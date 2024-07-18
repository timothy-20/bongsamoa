package com.timothy.bongsamoa.modules.temp;

import com.timothy.bongsamoa.modules.TKIntegerRange;
import com.timothy.bongsamoa.modules.TKMutableIntegerRange;

//interface TKCursor {
//    TKCursorPosition getPosition();
//    void movePrev();
//    void moveNext();
//    void moveFront();
//    void moveBack();
//}
//
//interface TKCursorPosition {
//
//}

public class TKTextCursor {
    @FunctionalInterface
    public interface CapacityObserver {
        int getCapacity();
    }

    public enum Mode {
        SINGLE,
        MULTI
    }

    public Mode mode;
    protected CapacityObserver capacityObserver;
    protected TKMutableIntegerRange position;

    public TKTextCursor(CapacityObserver capacityObserver) {
        this.mode = Mode.SINGLE;
        this.capacityObserver = capacityObserver;
        int capacity = this.capacityObserver.getCapacity();
        this.position = new TKMutableIntegerRange(capacity, capacity);
    }

    public TKIntegerRange getPosition() {
        return this.position;
    }

    public void moveTo(int point) {
        this.setPoint(point);
    }

    public void moveTo(int start, int end) {
        TKIntegerRange capacity = new TKIntegerRange(0, this.capacityObserver.getCapacity());

        if (capacity.contain(start) && capacity.contain(end)) {
            this.position.setRange(start, end);
        }
    }

    public void movePrev() {
        this.setPoint(this.position.getEnd() - 1);
    }

    public void moveNext() {
        this.setPoint(this.position.getEnd() + 1);
    }

    public void moveFront() {
        this.setPoint(0);
    }

    public void moveBack() {
        this.setPoint(this.capacityObserver.getCapacity());
    }

    protected void setPoint(int point) {
        switch (this.mode) {
            case SINGLE:
                this.position.setRange(point, point);
                break;

            case MULTI:
                this.position.setEnd(point);
                break;
        }
    }
}