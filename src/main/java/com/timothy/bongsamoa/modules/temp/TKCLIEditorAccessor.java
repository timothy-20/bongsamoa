package com.timothy.bongsamoa.modules.temp;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TKCLIEditorAccessor {
    private final FileChannel fileChannel;
    private int bufferSize;

    public TKCLIEditorAccessor(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
        this.bufferSize = 1024;
    }

    public void setBufferSize(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("버퍼의 크기는 최소 0보다 커야 합니다.");
        }

        this.bufferSize = bufferSize;
    }

    public int getSize() throws IOException {
        return (int)this.fileChannel.size();
    }

    // Readable
    public String read(int offset, int length) throws IOException {
        if (offset < 0 || length < 0) {
            throw new IllegalArgumentException("오프셋과 읽어올 문자의 길이는 최소 0보다 커야합니다.");
        }

        ByteBuffer buffer = ByteBuffer.allocate(this.bufferSize);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int readBytes, totalBytes = 0;

        if (length < this.bufferSize) {
            buffer.limit(length);
        }

        this.fileChannel.position(offset);

        while ((readBytes = this.fileChannel.read(buffer)) != -1) {
            outputStream.write(buffer.array(), 0, readBytes);
            buffer.clear();

            totalBytes += readBytes;

            if (length < totalBytes + this.bufferSize) {
                if (length == totalBytes) {
                    break;
                }

                buffer.limit(length - totalBytes);
            }
        }

        return outputStream.toString(StandardCharsets.UTF_8);
    }

    public String readAll() throws IOException {
        return this.read(0, Integer.MAX_VALUE);
    }

    // Writable
    // 0. 아예 새로운 내용을 입력할 수 있는 식의 write 함수 지원 여부에 대해 생각해 볼 것.
    // 1. 파일내의 문자열을 처리할 때, 접근하고 있는 라인에 대해서만 처리하도록 수정(replace 시 문자열 뒤로 밀기에서 발생하는 성능 문제).
    public void append(String text) throws IOException {
        if (text == null) {
            throw new IllegalArgumentException("더할 문자열의 값이 null 입니다.");
        }

        this.fileChannel.position((int)this.fileChannel.size());
        this.fileChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
    }

    public void insert(int offset, String text) throws IOException {
        if (offset < 0) {
            throw new IllegalArgumentException("문자열 삽입 시작 위치는 0보다 커야합니다.");
        }

        if (text == null) {
            throw new IllegalArgumentException("삽입할 문자열의 값이 null 입니다.");
        }

        this.replace(offset, 0, text);
    }

    public void delete(int offset, int length) throws IOException {
        if (offset < 0 || length < 0) {
            throw new IllegalArgumentException("문자열 삭제 시작 위치와 범위 값은 0보다 커야합니다.");
        }

        this.replace(offset, length, "");
    }

    public void replace(int offset, int length, String text) throws IOException {
        int size = (int)this.fileChannel.size();

        if (offset < 0 || length < 0) {
            throw new IllegalArgumentException("문자열 대체 시작 위치와 범위 값은 0보다 커야합니다.");
        }

        if (offset > size || offset + length > size) {
            throw new IllegalArgumentException("대체 범위가 본문을 초과했습니다.");
        }

        if (text == null) {
            throw new IllegalArgumentException("대체할 문자열의 값이 null 입니다.");
        }

        ByteBuffer buffer = ByteBuffer.allocate(size - (offset + length));

        // 대체 범위 뒤의 모든 바이트를 저장
        this.fileChannel.position(offset + length);
        this.fileChannel.read(buffer);
        buffer.flip();

        // 오프셋 위치에 문자열 쓰기
        this.fileChannel.position(offset);
        int writeBytes = this.fileChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));

        // 쓰여진 문자열 뒤에 이전에 저장해둔 바이트를 쓰기
        this.fileChannel.position(offset + writeBytes);
        int restWriteBytes = this.fileChannel.write(buffer);

        // 변경된 크기 이외의 바이트 제거
        this.fileChannel.truncate(offset + writeBytes + restWriteBytes);
    }

    // Copiable
    void copy(int start, int end) {

    }

    void cut(int start, int end) {

    }

    void paste(int index) {

    }

    void paste(int start, int end) {

    }
}
