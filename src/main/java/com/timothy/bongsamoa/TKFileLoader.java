package com.timothy.bongsamoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

interface TKLoadable {
    void save();
    void saveAs();
}

public class TKFileLoader implements TKLoadable {
    private File tempFile;
//    @Getter private final FileOutputStream fileOutputStream;

    public TKFileLoader(File file) throws FileNotFoundException, RuntimeException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getAbsolutePath());
        }

//        this.tempFile = new File(file.getParent(), "abcd_" + file.getName());
//        this.makeOnlyWritable(this.tempFile);
//
//        this.fileOutputStream = new FileOutputStream(this.tempFile);
    }

    public File createTempFile(File originalFile) throws IOException, InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            char value = random.nextBoolean() ? (char)(48 + random.nextInt(10)) : (char)(65 + random.nextInt(26));
            stringBuilder.append(value);
        }

        String tag = stringBuilder.toString();
        File tempFile = new File(originalFile.getParent(), tag + '_' + originalFile.getName());
        tempFile = this.setTempFileAttributes(tempFile);

        tempFile.deleteOnExit();
        return tempFile;
    }

    private File setTempFileAttributes(File file) throws IOException, InterruptedException {
        boolean flag = file.setExecutable(false);
        flag = flag && file.setReadable(false);
        flag = flag && file.setWritable(true);

        if (!flag) {
            throw new RuntimeException("쓰기 전용을 위한 임시 파일 권한 설정에 실패했습니다.");
        }

        return this.setHidden(file);
    }

    private File setHidden(File file) throws IOException, InterruptedException {
        String osInfo = System.getProperty("os.name").toLowerCase();

        if (osInfo.contains("win")) {
            String command = "attrib +H " + file.getAbsolutePath();
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

        } else {
            File newFile = new File(file.getParent(), '.' + file.getName());

            if (!file.delete()) {
                throw new RuntimeException("숨겨진 파일 생성을 위한 기존 파일 제거에 실패했습니다.");
            }

            if (!newFile.createNewFile()) {
                throw new RuntimeException("숨겨진 파일 생성에 실패했습니다.");
            }

            file = newFile;
        }

        return file;
    }

    @Override
    public void save() {

    }

    @Override
    public void saveAs() {

    }
}