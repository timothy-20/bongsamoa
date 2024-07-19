package com.timothy.bongsamoa.modules.temp;

import ch.qos.logback.core.joran.sanity.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TKTextEditor implements TKEditor {
    protected Path originalFile;
    protected Path tempFile;
    protected FileChannel fileChannel;

    public TKTextEditor() {
        this.originalFile = null;
        this.tempFile = null;
        this.fileChannel = null;
    }

    @Override
    public void create(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("열기 위한 파일 경로에 대한 문자열이 null입니다.");
        }

        Path newFile = this.createFile(Path.of(filePath));
        this.open(newFile.toString());
    }

    @Override
    public void open(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("열기 위한 파일 경로에 대한 문자열이 null입니다.");
        }

        Path originalFile = Path.of(filePath);

        if (!Files.exists(originalFile)) {
            throw new RuntimeException("파일이 존재하지 않습니다.");
        }

        if (!Files.isRegularFile(originalFile)) {
            throw new RuntimeException("경로 객체가 파일에 대한 것이 아닙니다.");
        }

        this.originalFile = originalFile;
        this.tempFile = this.createTempFile(this.originalFile, this.originalFile.getParent());
        this.fileChannel = FileChannel.open(this.tempFile, StandardOpenOption.WRITE);
    }

    @Override
    public void save() throws IOException {
        Files.copy(this.tempFile, this.originalFile);
        Files.setLastModifiedTime(this.originalFile, FileTime.fromMillis(System.currentTimeMillis()));
    }

    @Override
    public void saveAs(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("저장할 대상 파일 경로를 가져오지 못했습니다.");
        }

        Path targetFile = Path.of(filePath);

        if (Files.exists(targetFile)) {
            if (!Files.isRegularFile(targetFile)) {

            }

        } else {

        }

    }

    @Override
    public void restore() {

    }

    @Override
    public void restoreAs(int key) {

    }

    @Override
    public void close() throws IOException {
        if (this.fileChannel != null) {
            this.fileChannel.close();
        }
    }

    @Override
    public abstract TKTextEditorAccessor edit();
    public abstract TKTextCursor getCursor();
    public abstract String getContent();
    public abstract void undo();
    public abstract void redo();

    protected Path createFile(Path newFile) throws IOException {
        if (newFile == null) {
            throw new IllegalArgumentException("생성할 파일에 대한 경로값이 없습니다.");
        }

        if (Files.exists(newFile)) {
            // 생성하려는 파일이 존재하는 경우
            String newFileName = this.createDistinctFileName(newFile);
            newFile = Path.of(newFile.getParent().toString(), newFileName);
        }

        return Files.createFile(newFile);
    }

    private String createDistinctFileName(Path originalFile) throws IOException {
        if (originalFile == null) {
            throw new IllegalArgumentException("구분된 파일명을 생성할 원본 파일에 대한 경로값이 없습니다.");
        }

        Path currentDirectory = originalFile.getParent();
        Map<String, String> parsedOriginalFileName = this.parseFileName(originalFile);
        String name = parsedOriginalFileName.get("name");
        String extension = parsedOriginalFileName.get("extension");
        Stream<Path> files = Files.list(currentDirectory);
        // 현재 디렉토리에 존재하는 중복된 파일의 이름을 가져옴
        // 이 때 현재 함수에서 생성하는 파일명의 형식과 동일한 이름의 파일도 가져옴
        List<Path> filteredFiles = files
                .filter(Files::isRegularFile)
                .filter((Path file) -> file.getFileName().toString().matches(name + "[0-9]+" + extension))
                .collect(Collectors.toList());
        files.close();

        // 파일명 뒤에 구분을 위해 붙이는 숫자는 가장 큰 숫자에 1만큼 더 큰 숫자로 함
        // 숫자가 붙어있지 않는 경우(원본 파일만 있는 경우), 0 + 1 이므로 자연히 1이 들어감
        int maxNumber = 0;

        for (Path element : filteredFiles) {
            Map<String, String> parsedFileName = this.parseFileName(element);
            int number = Integer.parseInt(parsedFileName.get("name").replace(name, ""));

            if (number > maxNumber) {
                maxNumber = number;
            }
        }

        return name + (maxNumber + 1) + extension;
    }

    private Map<String, String> parseFileName(Path file) {
        if (file == null) {
            throw new IllegalArgumentException("이름과 확장자를 구분할 파일에 대한 값을 받지 못했습니다.");
        }

        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        Map<String, String> result = new HashMap<>(2);
        result.put("name", fileName.substring(0, dotIndex));
        result.put("extension", fileName.substring(dotIndex));
        return result;
    }

    protected Path createTempFile(Path originalFile, Path directory) throws IOException {
        if (originalFile == null || !Files.isRegularFile(originalFile)) {
            throw new IllegalArgumentException("값이 없거나 실제로 존재하지 않는 원본 파일 객체입니다.");
        }

        if (directory == null || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("임시 파일을 위한 디렉토리 객체가 정상적이지 않습니다.");
        }

        // 확장자를 제거한 파일명 가져오기
        String fileName = originalFile.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(dotIndex);
        fileName = fileName.substring(0, dotIndex);

        // 임시 파일 생성
        return Files.createTempFile(directory, fileName, fileExtension);
    }
}