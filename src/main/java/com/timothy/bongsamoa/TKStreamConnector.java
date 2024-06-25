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