package com.timothy.bongsamoa.modules;

import jakarta.annotation.Nonnull;

import java.nio.CharBuffer;

public class TKDocument {
    public TKDocument() {

    }
}

//public class TKDocument implements Appendable, Readable {
//    // 문서 내에 문자열로 구성된 본분
//    protected StringBuilder stringBuilder;
//    protected TKCursor<Integer> cursor;
//    protected int readIndex;
//
//    public TKDocument() {
//        this.stringBuilder = new StringBuilder();
//        this.cursor = new Cursor();
//        this.readIndex = 0;
//    }
//
//    public TKMutableIntegerRange getCursor() {
//        return this.cursor;
//    }
//
//    public TKDocument insert(String str) {
//        int cursorEnd = this.cursor.getEnd();
//
//        if (this.cursor.isPoint()) {
//            this.stringBuilder.insert(cursorEnd, str);
//
//            int point = cursorEnd + str.length();
//            this.cursor.setRange(point, point);
//
//        } else {
//            int cursorStart = this.cursor.getStart();
//            this.stringBuilder.replace(cursorStart, cursorEnd, str);
//
//            int point = Math.min(cursorStart, cursorEnd) + str.length();
//            this.cursor.setRange(point, point);
//        }
//
//        return this;
//    }
//
//    public TKDocument delete() {
//        int cursorEnd = this.cursor.getEnd();
//
//        if (this.cursor.isPoint()) {
//            this.stringBuilder.deleteCharAt(cursorEnd);
//
//            int point = cursorEnd - 1;
//            this.cursor.setRange(point, point);
//
//        } else {
//            int cursorStart = this.cursor.getStart();
//            this.stringBuilder.delete(cursorStart, cursorEnd);
//
//            int point = Math.min(cursorStart, cursorEnd);
//            this.cursor.setRange(point, point);
//        }
//
//        return this;
//    }
//
//    @Override
//    public TKDocument append(char c) {
//        this.cursor.moveBack();
//        return this.insert(String.valueOf(c));
//    }
//
//    @Override
//    public TKDocument append(CharSequence csq) {
//        this.cursor.moveBack();
//        return this.insert(String.valueOf(csq));
//    }
//
//    @Override
//    public TKDocument append(CharSequence csq, int start, int end) {
//        this.cursor.moveBack();
//
//        if (csq != null) {
//            this.insert(String.valueOf(csq.subSequence(start, end)));
//
//        } else {
//            this.insert("null");
//        }
//
//        return this;
//    }
//
//    @Override
//    public int read(@Nonnull CharBuffer cb) throws NullPointerException {
//        if (cb == null) {
//            throw new NullPointerException();
//        }
//
//        int remainContextLength = this.stringBuilder.length() - this.readIndex;
//
//        if (remainContextLength <= 0) {
//            return -1;
//        }
//
//        int remainBufferLength = cb.remaining();
//        int length = Math.min(remainContextLength, remainBufferLength);
//        char[] ch = new char[length];
//
//        this.stringBuilder.getChars(this.readIndex, this.readIndex + length, ch, 0);
//        cb.put(ch, 0, length);
//
//        this.readIndex += length;
//        return length;
//    }
//}