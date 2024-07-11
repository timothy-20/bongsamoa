package com.timothy.bongsamoa.modules;

public class TKTextSingleCursor extends TKSingleCursor implements TKCursorCopy, TKCursorSearch {
    protected StringBuilder stringBuilder;

    public TKTextSingleCursor(CharSequence cs) {
        super();
        this.stringBuilder = new StringBuilder(cs);
        this.position = cs.length();
    }

    @Override
    public Integer getCapacity() {
        return this.stringBuilder.length();
    }

    @Override
    public char getCurrentCharacter() {
        int newPosition = this.position.equals(this.getCapacity()) ? this.position - 1 : this.position;
        return this.stringBuilder.charAt(newPosition);
    }

    @Override
    public void copy() {

    }

    @Override
    public void cut() {

    }

    @Override
    public void paste() {

    }

    @Override
    public Integer[] search() {
        return new Integer[0];
    }
}