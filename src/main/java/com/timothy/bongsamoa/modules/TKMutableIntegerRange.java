package com.timothy.bongsamoa.modules;

public class TKMutableIntegerRange extends TKIntegerRange {
    public TKMutableIntegerRange(Integer start, Integer end) {
        super(start, end);
    }

    public TKMutableIntegerRange(TKIntegerRange other) {
        super(other.start, other.end);
    }

    public TKMutableIntegerRange() {
        super(0, 0);
    }

    public void setStart(Integer start) throws NullPointerException {
        if (start == null) {
            throw new NullPointerException("start is null.");
        }

        this.start = start;
    }

    public void setEnd(Integer end) throws NullPointerException {
        if (end == null) {
            throw new NullPointerException("end is null.");
        }

        this.end = end;
    }

    public void setRange(Integer start, Integer end) throws NullPointerException {
        if (start == null || end == null) {
            throw new NullPointerException("start or end is null.");
        }

        this.start = start;
        this.end = end;
    }
}
