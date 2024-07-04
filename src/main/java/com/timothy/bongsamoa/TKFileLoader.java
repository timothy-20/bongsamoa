package com.timothy.bongsamoa;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.*;
import java.util.*;

public class TKFileLoader implements TKLoadable, AutoCloseable {
    private final File originalFile;
    private final File tempFile;
    @Getter private final FileChannel fileChannel;

    public TKFileLoader(@Nonnull File targetFile, @Nullable File tempDirectory) throws IOException, RuntimeException {
        if (targetFile == null || !targetFile.isFile()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 대상 파일 객체입니다.");
        }

        this.originalFile = targetFile;
        this.lockFile(this.originalFile);

        this.tempFile = this.createTempFile(this.originalFile, tempDirectory != null ? tempDirectory : this.originalFile.getParentFile());
        this.fileChannel = FileChannel.open(this.tempFile.toPath(), StandardOpenOption.WRITE);
    }

    public TKFileLoader(@Nonnull File targetFile) throws IOException, RuntimeException {
        this(targetFile, null);
    }

    @Override
    public void close() throws Exception {
        if (this.fileChannel != null) {
            this.fileChannel.close();
        }

        this.unlockFile(this.originalFile);
    }

    @Override
    public void save() throws IOException {
        this.unlockFile(this.originalFile);
        this.copyTo(this.tempFile, this.originalFile);
        this.lockFile(this.originalFile);
    }

    @Override
    public void saveAs(File newFile) throws Exception {
        if (newFile == null) {
            throw new IllegalArgumentException("값이 없는 파일 객체입니다.");
        }

        if (newFile.isFile()) {
            this.copyTo(this.tempFile, newFile);

        } else {
            FileInputStream tempFileInputStream = new FileInputStream(this.tempFile);
            Files.copy(tempFileInputStream, newFile.toPath());
            tempFileInputStream.close();
        }
    }

    public void hideTempFile(boolean flag) throws IOException {
        this.setHidden(this.tempFile, flag);
    }

    public void deleteTempFileOnExit() {
        this.tempFile.deleteOnExit();
    }

    private File createTempFile(File originalFile, File directory) throws IOException {
        if (originalFile == null || !originalFile.isFile()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 원본 파일 객체입니다.");
        }

        if (directory == null || !directory.isDirectory()) {
            throw new IllegalArgumentException("임시 파일을 위한 디렉토리 객체가 정상적이지 않습니다.");
        }

        // 확장자를 제거한 파일명 가져오기
        String fileName = originalFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(dotIndex);
        fileName = fileName.substring(0, dotIndex);

        // 임시 파일 생성
        return Files.createTempFile(directory.toPath(), fileName, fileExtension).toFile();
    }

    private void setHidden(File file, boolean flag) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 숨김 파일 혹은 디렉토리 객체입니다.");
        }

        // 파일 특성 숨김 처리
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(file.toPath(), DosFileAttributeView.class);
        dosFileAttributeView.setHidden(flag);
    }

    private UserPrincipal getCurrentUserPrincipal(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 숨김 파일 혹은 디렉토리 객체입니다.");
        }

        String currentUsername = System.getProperty("user.name");
        UserPrincipalLookupService userPrincipalLookupService = file.toPath().getFileSystem().getUserPrincipalLookupService();

        return userPrincipalLookupService.lookupPrincipalByName(currentUsername);
    }

    // thread safe 하게 구현할 수 있는 방법을 고안할 것
    private void lockFile(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 숨김 파일 혹은 디렉토리 객체입니다.");
        }

        Path originalFilePath = file.toPath();
        // 현재 사용자의 upn 가져오기
        UserPrincipal currentUserPrincipal = this.getCurrentUserPrincipal(file);
        // acl 권한 거부에 대한 설정 객체 생성하기
        AclEntry denyAclEntry = AclEntry.newBuilder()
                .setType(AclEntryType.DENY)
                .setPermissions(
                        AclEntryPermission.WRITE_ACL,
                        AclEntryPermission.WRITE_DATA,
                        AclEntryPermission.READ_DATA,
                        AclEntryPermission.EXECUTE
                )
                .setPrincipal(currentUserPrincipal)
                .build();

        // 새로운 acl 설정 추가
        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(originalFilePath, AclFileAttributeView.class);
        List<AclEntry> aclEntries = aclFileAttributeView.getAcl();
        aclEntries.add(0, denyAclEntry);
        aclFileAttributeView.setAcl(aclEntries);
    }

    private void unlockFile(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 숨김 파일 혹은 디렉토리 객체입니다.");
        }

        UserPrincipal currentUserPrincipal = this.getCurrentUserPrincipal(file);
        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(file.toPath(), AclFileAttributeView.class);
        List<AclEntry> aclEntryList = aclFileAttributeView.getAcl();

        if (aclEntryList.removeIf(aclEntry -> aclEntry.type() == AclEntryType.DENY && aclEntry.principal().equals(currentUserPrincipal))) {
            aclFileAttributeView.setAcl(aclEntryList);
        }
    }

    private void copyTo(@Nonnull File originalFile, @Nonnull File targetFile) throws IOException {
        if (originalFile == null || !originalFile.isFile()) {
            throw new IllegalArgumentException("값이 없거나 파일이 아닌 원본 파일 객체입니다.");
        }

        if (targetFile == null || !targetFile.isFile()) {
            throw new IllegalArgumentException("값이 없거나 파일이 아닌 대상 파일 객체입니다.");
        }

        FileOutputStream targetFileOutputStream = new FileOutputStream(targetFile);
        Files.copy(originalFile.toPath(), targetFileOutputStream);
        targetFileOutputStream.close();

        Files.setLastModifiedTime(targetFile.toPath(), FileTime.fromMillis(System.currentTimeMillis()));
    }
}