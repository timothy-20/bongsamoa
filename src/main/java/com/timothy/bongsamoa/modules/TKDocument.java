package com.timothy.bongsamoa.modules;

import lombok.Getter;

import java.nio.CharBuffer;

public class TKDocument implements Appendable, Readable {
    // 문서 객체의 커서를 위한 일종의 옵저버 역할을 하는 내부 클래스
    protected static class Cursor extends TKMutableIntegerRange {
        @Override
        public void setStart(Integer start) {
            super.setStart(start);

            if (start >= 0) {
                this.start = start;
            }
        }

        @Override
        public void setEnd(Integer end) {
            super.setEnd(end);

            if (end >= 0) {
                this.end = end;
            }
        }

        @Override
        public void setRange(Integer start, Integer end) {
            super.setRange(start, end);

            if (start >= 0 && end >= 0) {
                this.start = start;
                this.end = end;
            }
        }

        public boolean isPoint() {
            return this.start.compareTo(this.end) == 0;
        }
    }

    // 문서 내에 문자열로 구성된 본분
    protected StringBuilder stringBuilder;
    @Getter protected TKMutableIntegerRange cursor;
    protected int readIndex;

    public TKDocument() {
        this.stringBuilder = new StringBuilder();
        this.cursor = new Cursor();
        this.readIndex = 0;
    }

    public TKDocument insert(CharSequence csq) {
//        this.stringBuilder.insert
//        this.stringBuilder.replace()
//        this.stringBuilder.setCharAt();

        return this;
    }

    public TKDocument delete() {
//        this.stringBuilder.delete()
//        this.stringBuilder.deleteCharAt()

        return this;
    }

    @Override
    public TKDocument append(char c) {
        if (this.cursor.getEnd() == this.stringBuilder.length()) {
            // 커서가 문자열의 마지막에 위치해 있는 경우


        } else {
            // 커서가 문자열 중간에 위치한 경우

        }

        return this;
    }

    @Override
    public TKDocument append(CharSequence csq) {
        this.stringBuilder.append(csq);

        int length = this.stringBuilder.length();
        this.cursor.setRange(length, length);

        return this;
    }

    @Override
    public TKDocument append(CharSequence csq, int start, int end) {
        if (csq != null) {
            this.append(csq.subSequence(start, end));

        } else {
            this.append("null");
        }

        return this;
    }

    @Override
    public int read(CharBuffer cb) throws NullPointerException {
        if (cb == null) {
            throw new NullPointerException();
        }

        int remainContextLength = this.stringBuilder.length() - this.readIndex;

        if (remainContextLength <= 0) {
            return -1;
        }

        int remainBufferLength = cb.remaining();
        int length = Math.min(remainContextLength, remainBufferLength);
        char[] ch = new char[length];

        this.stringBuilder.getChars(this.readIndex, this.readIndex + length, ch, 0);
        cb.put(ch, 0, length);

        this.readIndex += length;
        return length;
    }
}