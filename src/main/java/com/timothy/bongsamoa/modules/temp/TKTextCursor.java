package com.timothy.bongsamoa.modules.temp;

import com.timothy.bongsamoa.modules.TKIntegerRange;
import com.timothy.bongsamoa.modules.TKMutableIntegerRange;

public class TKTextCursor {
    public enum Mode {
        SINGLE,
        MULTI
    }

    protected TKMutableIntegerRange position;
    protected int capacity;
    protected Mode mode;

    public TKTextCursor(String content) {

    }

    public TKTextCursor(int start, int end) {
        this.position = new TKMutableIntegerRange(start, end);
        this.limit = 0;
        this.mode = Mode.SINGLE;
    }

    public void setLimit(int limit) {
        if (limit > 0) {
            this.limit = limit;
        }
    }

    public TKIntegerRange getPosition() {
        return this.position;
    }

    public void moveTo(int start, int end) {
        TKIntegerRange capacity;

        if (this.limit > 0) {
            capacity = new TKIntegerRange(0, this.limit);

        } else {
            capacity = new TKIntegerRange(0, Math.max(start, end));
        }

        if (capacity.contain(start) && capacity.contain(end)) {
            this.position.setRange(start, end);
        }
    }

    // 단일 커서 상태 및 다중 커서 상태 각각에 대해 구현이 달라야 함
    public void movePrev() {
        int newPosition = this.position.getEnd() - 1;

        if (newPosition >= 0) {
            if (this.position.getStart().equals(this.position.getEnd())) {
                this.position.setRange(newPosition, newPosition);

            } else {
                this.position.setEnd(newPosition);
            }
        }
    }

    public void moveNext() {
        int newPosition = this.position.getEnd() + 1;

        if (this.limit > 0 && this.limit < newPosition) {
            newPosition = this.limit;
        }

        if (this.position.getStart().equals(this.position.getEnd())) {
            this.position.setRange(newPosition, newPosition);

        } else {
            this.position.setEnd(newPosition);
        }
    }

    public void moveFront() {
        if (this.position.getStart().equals(this.position.getEnd())) {
            this.position.setRange(0, 0);

        } else {
            this.position.setEnd(0);
        }
    }

    public void moveBack() {
        if (this.position.getStart().equals(this.position.getEnd())) {

        } else {

        }

        this.position.setEnd(this.getCapacity());
    }
}