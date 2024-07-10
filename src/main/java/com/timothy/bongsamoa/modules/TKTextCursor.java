package com.timothy.bongsamoa.modules;

public class TKTextCursor implements TKCursor<Integer> {
    protected StringBuilder stringBuilder;
    protected Integer position;

    public TKTextCursor(String text) {
        this.stringBuilder = new StringBuilder(text);
        this.position = this.stringBuilder.length();
    }

    public TKTextCursor() {
        this("");
    }

    @Override
    public Integer getPosition() {
        return this.position;
    }

    @Override
    public void moveTo(Integer position) {
        this.position = position;
    }

    @Override
    public void movePrev() {
        if (this.position > 0) {
            this.position--;
        }
    }

    @Override
    public void moveNext() {
        if (this.position < this.stringBuilder.length()) {
            this.position++;
        }
    }

    @Override
    public void moveFront() {
        this.position = 0;
    }

    @Override
    public void moveBack() {
        this.position = this.stringBuilder.length();
    }
}