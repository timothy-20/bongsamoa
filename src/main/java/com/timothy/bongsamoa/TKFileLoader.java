package com.timothy.bongsamoa;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.*;

interface TKLoadable {
    void save();
    void saveAs();
}

public class TKFileLoader implements TKLoadable {
    private File originalFile;
    private File tempFile;
    @Getter private final FileOutputStream fileOutputStream;

    public TKFileLoader(File file) throws IOException, RuntimeException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        this.originalFile = file;
        this.tempFile = this.createTempFile(file);
        this.fileOutputStream = new FileOutputStream(this.tempFile);
    }

    private File createTempFile(File originalFile) throws IOException {
        // 확장자를 제거한 파일명 가져오기
        String originalFileName = originalFile.getName();
        originalFileName = originalFileName.substring(0, originalFileName.lastIndexOf('.'));
        // 임시 파일 생성
        Path tempFilePath = Files.createTempFile(originalFile.getParentFile().toPath(), originalFileName, null);
        return this.setTempFileAttributes(tempFilePath.toFile());
    }

    private File setTempFileAttributes(File tempFile) throws IOException {
        Path tempFilePath = tempFile.toPath();
        // 현재 사용자의 upn 가져오기
        UserPrincipalLookupService userPrincipalLookupService = tempFilePath.getFileSystem().getUserPrincipalLookupService();
        String currentUsername = System.getProperty("user.name");
        UserPrincipal currentUserPrincipal = userPrincipalLookupService.lookupPrincipalByName(currentUsername);
        // acl 권한 거부에 대한 설정 객체 생성하기
        AclEntry denyAclEntry = AclEntry.newBuilder()
                .setType(AclEntryType.DENY)
                .setPermissions(AclEntryPermission.EXECUTE, AclEntryPermission.READ_DATA)
                .setPrincipal(currentUserPrincipal)
                .build();

        // 새로운 acl 설정 추가
        AclFileAttributeView aclFileAttributeView = Files.getFileAttributeView(tempFilePath, AclFileAttributeView.class);
        List<AclEntry> aclEntries = aclFileAttributeView.getAcl();
        aclEntries.add(0, denyAclEntry);

        Files.setAttribute(tempFilePath, "acl:acl", aclEntries);
        // 파일 숨김 설정
        Files.setAttribute(tempFilePath, "dos:hidden", true);
        // 프로세스 종료 시, 임시 파일이 제거되도록 트리거
        tempFilePath.toFile().deleteOnExit();
        return tempFilePath.toFile();
    }

    @Override
    public void save() {

    }

    @Override
    public void saveAs() {

    }
}