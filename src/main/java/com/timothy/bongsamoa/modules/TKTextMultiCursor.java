package com.timothy.bongsamoa.modules;

public class TKTextMultiCursor implements TKCursor<TKIntegerRange> {
    protected StringBuilder stringBuilder;
    protected TKMutableIntegerRange position;

    public TKTextMultiCursor(String text) {
        this.stringBuilder = new StringBuilder(text);
        this.position = new TKMutableIntegerRange(text.length(), text.length());
    }

    @Override
    public TKIntegerRange getPosition() {
        return this.position;
    }

    @Override
    public void moveTo(TKIntegerRange position) {
        TKIntegerRange capacity = new TKIntegerRange(0, this.stringBuilder.length());

        if (capacity.contain(position.getStart()) && capacity.contain(position.getEnd())) {
            this.position.setRange(position.getStart(), position.getEnd());
        }
    }

    @Override
    public void movePrev() {
        int newPosition = this.position.getEnd() - 1;

        if (newPosition >= 0) {
            this.position.setEnd(newPosition);
        }
    }

    @Override
    public void moveNext() {
        int newPosition = this.position.getEnd() + 1;

        if (newPosition <= this.stringBuilder.length()) {
            this.position.setEnd(newPosition);
        }
    }

    @Override
    public void moveFront() {
        this.position.setEnd(0);
    }

    @Override
    public void moveBack() {
        this.position.setEnd(this.stringBuilder.length());
    }
}
