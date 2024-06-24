package com.timothy.bongsamoa;

import lombok.Getter;

import java.io.*;
import java.net.URL;

@Getter
public class TKStreamConnector {
    private final int bufferSize;
    private long totalDataSize;

    public TKStreamConnector() {
        this.bufferSize = 1024;
        this.totalDataSize = 0;
    }

    public TKStreamConnector(int bufferSize) {
        this.bufferSize = bufferSize;
        this.totalDataSize = 0;
    }

    public boolean pipe(InputStream inputStream, OutputStream outputStream) {
        boolean flag = false;

        try {
            int readDataSize;
            byte[] buffer = new byte[this.bufferSize];

            while ((readDataSize = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readDataSize);
                this.totalDataSize += readDataSize;
            }

            flag = true;

        } catch (Exception e) {
            System.out.println("데이터를 읽는데 실패했습니다. 사유: " + e.getMessage());

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }

            } catch (Exception e) {
                System.out.println("입력 스트림을 마무리하는데 실패했습니다. 사유: " + e.getMessage());
            }
        }

        return flag;
    }
}

// 1. open url
// 2. http response
class TK {
    private final TKStreamConnector streamConnector;

    public TK() {
        this.streamConnector = new TKStreamConnector();
    }

    public TK(int bufferSize) {
        this.streamConnector = new TKStreamConnector(bufferSize);
    }

    public TK(URL sourceURL, URL destinationURL) {
        InputStream inputStream = sourceURL.openStream();
        File destinationFile = new File(destinationURL.getFile());
    }

    public void test() {
        TKStreamConnector streamConnector = new TKStreamConnector();
        URL sourceURL = new URL("https://blog.kakaocdn.net/dn/MThfh/btrRtcbb2Xl/zR5vUkvvJNLOPo7kXhkQHK/img.png");
        InputStream inputStream = sourceURL.openStream();
        File destinationFile = new File("C:\\Users\\timothy\\IdeaProjects\\demo\\temp", "test1.png");
        FileOutputStream outputStream = new FileOutputStream(destinationFile);

        this.streamConnector.pipe()
    }
}