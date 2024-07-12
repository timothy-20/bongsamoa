package com.timothy.bongsamoa.modules;

public interface TKModifiable<T> {
    void insert(int offset, T value);
    void remove(int offset);
    void remove(int offset, int count);
}

class TKTextEditor {
    protected TKCursor cursor;
    protected StringBuilder stringBuilder;

    public TKTextEditor(TKCursor cursor, CharSequence cs) {
        this.cursor = cursor;
        this.stringBuilder = new StringBuilder(cs);
    }

    TKCursor getCursor() {
        return this.cursor;
    }

    public void insert(CharSequence value) {
        this.stringBuilder.insert(this.cursor.getPosition(), value);
        this.cursor.moveTo(this.cursor.getPosition() + value.length());
    }

    public void remove() {
        int position = this.cursor.getPosition();

        if (position == this.stringBuilder.length()) {
            this.stringBuilder.delete(position - 1, position);

        } else {
            this.stringBuilder.delete(position, position + 1);
        }
    }
}

class TKTextSingleCursorOperator {
    public TKTextSingleCursorOperator() {
        TKSingleCursor cursor = new TKTextSingleCursor("aaa");
        TKTextEditor<Integer> editor = new TKTextEditor<>(cursor, "aaa");
    }
}