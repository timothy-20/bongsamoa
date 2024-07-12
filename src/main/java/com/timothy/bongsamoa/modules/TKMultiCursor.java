package com.timothy.bongsamoa.modules;

public abstract class TKMultiCursor implements TKCursor {
    protected TKMutableIntegerRange position;

    public TKMultiCursor() {
        this.position = new TKMutableIntegerRange(0, 0);
    }

    public abstract Integer getCapacity();

    @Override
    public TKIntegerRange getPosition() {
        return this.position;
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

        if (newPosition <= this.getCapacity()) {
            this.position.setEnd(newPosition);
        }
    }

    @Override
    public void moveFront() {
        this.position.setEnd(0);
    }

    @Override
    public void moveBack() {
        this.position.setEnd(this.getCapacity());
    }
}
