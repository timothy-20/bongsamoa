package com.timothy.bongsamoa;

import lombok.Getter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.*;

interface TKLoadable {
    void save() throws Exception;
    void saveAs(File newFile) throws Exception;
}

public class TKFileLoader implements TKLoadable, AutoCloseable {
    private final File originalFile;
    private final File tempFile;
    @Getter private final FileOutputStream fileOutputStream;

    public TKFileLoader(File targetFile) throws IOException, RuntimeException {
        if (!targetFile.exists()) {
            throw new FileNotFoundException(targetFile.getAbsolutePath());
        }

        this.originalFile = targetFile;
        this.tempFile = this.createTempFile(this.originalFile);
        this.fileOutputStream = new FileOutputStream(this.tempFile);

        this.lockFile(this.originalFile);
    }

    @Override
    public void close() throws Exception {
        if (this.fileOutputStream != null) {
            this.fileOutputStream.close();
        }
    }

    @Override
    public void save() throws IOException {
        this.saveTo(this.tempFile, this.originalFile);
    }

    @Override
    public void saveAs(File newFile) throws Exception {
        if (newFile.exists()) {
            this.saveTo(this.tempFile, newFile);

        } else {
//            File newFileDirectory = newFile.getParentFile();
//
//            if (!newFileDirectory.exists()) {
//                if (!newFileDirectory.mkdirs()) {
//                    throw new RuntimeException("다른 이름으로 저장할 파일을 위한 경로를 생성하지 못했습니다.");
//                }
//            }

            FileInputStream tempFileInputStream = new FileInputStream(this.tempFile);
            Files.copy(tempFileInputStream, newFile.toPath());
            tempFileInputStream.close();

            System.out.println("abcd");
        }
    }

    // 임시 파일 삭제 여부에 대한 옵션도 두면 좋을 듯 하다(좀 더 고려해볼 것)
    private File createTempFile(File originalFile) throws IOException {
        // 확장자를 제거한 파일명 가져오기
        String originalFileName = originalFile.getName();
        int dotIndex = originalFileName.lastIndexOf('.');
        String originalFileExtension = originalFileName.substring(dotIndex);
        originalFileName = originalFileName.substring(0, dotIndex);
        // 임시 파일 생성
        Path tempFilePath = Files.createTempFile(originalFile.getParentFile().toPath(), originalFileName, originalFileExtension);
        this.setTempFileAttributes(tempFilePath.toFile());
        // 프로세스 종료 시, 임시 파일이 제거되도록 트리거
        File tempFile = tempFilePath.toFile();
        tempFile.deleteOnExit();
        return tempFile;
    }

    private void setTempFileAttributes(File tempFile) throws IOException {
        Path tempFilePath = tempFile.toPath();
        // 파일 특성 숨김 처리
        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(tempFilePath, DosFileAttributeView.class);
        dosFileAttributeView.setHidden(true);
    }

    private void lockFile(File file) throws IOException {
        Path originalFilePath = file.toPath();
        // 현재 사용자의 upn 가져오기
        String currentUsername = System.getProperty("user.name");
        UserPrincipalLookupService userPrincipalLookupService = originalFilePath.getFileSystem().getUserPrincipalLookupService();
        UserPrincipal currentUserPrincipal = userPrincipalLookupService.lookupPrincipalByName(currentUsername);
        // acl 권한 거부에 대한 설정 객체 생성하기
        AclEntry denyAclEntry = AclEntry.newBuilder()
                .setType(AclEntryType.DENY)
                .setPermissions(
                        AclEntryPermission.WRITE_ACL,
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

    }

    private void saveTo(File file, File targetFile) throws IOException {
        FileOutputStream targetFileOutputStream = new FileOutputStream(targetFile);
        Files.copy(file.toPath(), targetFileOutputStream);
        targetFileOutputStream.close();

        Files.setLastModifiedTime(targetFile.toPath(), FileTime.fromMillis(System.currentTimeMillis()));
    }
}