package com.timothy.bongsamoa;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

// 고려할 사항들
// 1. open url
// 2. http response
public class TKMakeshift {
    //    private final TKStreamConnector streamConnector;

    public TKMakeshift(String destinationFilePath) {

    }

    public TKMakeshift(File destinationFile) {

    }

    public void test() {
//        TKStreamConnector streamConnector = new TKStreamConnector();
//        URL sourceURL = new URL("https://blog.kakaocdn.net/dn/MThfh/btrRtcbb2Xl/zR5vUkvvJNLOPo7kXhkQHK/img.png");
//        InputStream inputStream = sourceURL.openStream();
//        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\demo\\temp", "test1.png");
//        FileOutputStream outputStream = new FileOutputStream(destinationFile);
//
//        this.streamConnector.pipe()
    }

    public void prepareFile(File destinationFile) throws IOException, RuntimeException {
        File currentDirectory;

        if (destinationFile.exists()) {
            String name, extension = ".txt";

            if (destinationFile.isFile()) {
                currentDirectory = destinationFile.getParentFile();
                String filename  = destinationFile.getName();
                int dotIndex = filename.lastIndexOf('.');
                name = filename.substring(0, dotIndex);
                extension = filename.substring(dotIndex);

            } else {
                name = "untitled";

                if (destinationFile.isDirectory()) {
                    currentDirectory = destinationFile;

                } else {
                    throw new RuntimeException("목적지의 대상이 파일과 디렉토리 모두 해당되지 않습니다.");
                }
            }

            String[] filenames = currentDirectory.list((dir, filename) -> filename.toLowerCase().contains(name));

            if (filenames == null) {
                throw new RuntimeException("대상 디렉토리 상에 있는 파일명을 가져올 수 없습니다.");
            }

            String newFilename = name + filenames.length + extension;
            File newFile = new File(currentDirectory.getPath(), newFilename);

            if (!newFile.createNewFile()) {
                throw new RuntimeException("새로운 파일을 생성할 수 없습니다.");
            }

        } else {
            currentDirectory = destinationFile.getParentFile();

            if (!currentDirectory.exists()) {
                if (!currentDirectory.mkdirs()) {
                    throw new RuntimeException("새로운 파일이 위치할 경로를 생성할 수 없습니다.");
                }
            }

            String filename = destinationFile.getName();
            int dotPosition = filename.lastIndexOf('.');

            if (dotPosition == -1) {
                if (!destinationFile.mkdir()) {
                    throw new RuntimeException("새로운 파일이 위치할 디렉토리를 생성할 수 없습니다.");
                }

                File newFile = new File(destinationFile.getPath(), "untitled0.txt");

                if (!newFile.createNewFile()) {
                    throw new RuntimeException("새로운 파일을 생성할 수 없습니다.");
                }

            } else {
                if (!destinationFile.createNewFile()) {
                    throw new RuntimeException("새로운 파일을 생성할 수 없습니다.");
                }
            }
        }
    }
}