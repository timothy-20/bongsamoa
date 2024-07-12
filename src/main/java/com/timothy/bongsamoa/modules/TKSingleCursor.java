package com.timothy.bongsamoa.modules;

public abstract class TKSingleCursor implements TKCursor {
    protected Integer position;

    public TKSingleCursor() {
        this.position = 0;
    }

    public abstract Integer getCapacity();

    @Override
    public Integer getPosition() {
        return this.position;
    }

    @Override
    public void movePrev() {
        if (this.position > 0) {
            this.position--;
        }
    }

    @Override
    public void moveNext() {
        if (this.position < this.getCapacity()) {
            this.position++;
        }
    }

    @Override
    public void moveFront() {
        this.position = 0;
    }

    @Override
    public void moveBack() {
        this.position = this.getCapacity();
    }
}
