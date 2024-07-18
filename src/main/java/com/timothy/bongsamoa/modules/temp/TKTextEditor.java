package com.timothy.bongsamoa.modules.temp;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class TKTextEditor implements TKEditor {
    protected Path originalFile;
    protected Path tempFile;
    protected FileChannel fileChannel;

    public TKTextEditor() {

    }

    @Override
    public void create(String filePath) throws IOException {
        if (filePath == null) {
            throw new IllegalArgumentException("열기 위한 파일 경로에 대한 문자열이 null입니다.");
        }

        Path newFile = Path.of(filePath);

        if (Files.exists(newFile)) {
            Path currentDirectory = newFile.getParent();
            String fileName = newFile.getFileName().toString();
            int dotIndex = fileName.lastIndexOf('.');
            String name = fileName.substring(0, dotIndex);
            String extension = fileName.substring(dotIndex);
            Stream<Path> files = Files.list(currentDirectory);
            List<Path> filteredFiles = files
                    .filter((Path file) -> file.getFileName().toString().toLowerCase().contains(name))
                    .collect(Collectors.toList());
            String newFileName = name + filteredFiles.size() + extension;
            newFile = Path.of(currentDirectory.toString(), newFileName);
            files.close();
        }

        Files.createFile(newFile);
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
    public void save() {

    }

    @Override
    public void saveAs(String filePath) {

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

