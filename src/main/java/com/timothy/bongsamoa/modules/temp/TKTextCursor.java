package com.timothy.bongsamoa.modules.temp;

import com.timothy.bongsamoa.modules.TKIntegerRange;
import com.timothy.bongsamoa.modules.TKMutableIntegerRange;

public class TKTextCursor {
    public enum Mode {
        SINGLE,
        MULTI
    }

    protected TKMutableIntegerRange position;
    protected int limit;
    protected int capacity;
    protected Mode mode;

    public TKTextCursor(int start, int end) {
        this.position = new TKMutableIntegerRange(start, end);
        this.capacity = Math.max(start, end);
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

    }

    // 단일 커서 상태 및 다중 커서 상태 각각에 대해 구현이 달라야 함
    public void movePrev() {
        int newPosition = this.position.getEnd() - 1;

        if (newPosition >= 0) {
            this.position.setEnd(newPosition);
        }
    }

    public void moveNext() {
        int newPosition = this.position.getEnd() + 1;

        if (this.limit > 0 && this.limit >= newPosition) {
            this.position.setEnd(newPosition);
        }
    }

    public void moveFront() {
        this.position.setEnd(0);
    }

    public void moveBack() {
        this.position.setEnd(this.getCapacity());
    }
}