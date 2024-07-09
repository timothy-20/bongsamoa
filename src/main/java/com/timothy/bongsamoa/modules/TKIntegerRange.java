package com.timothy.bongsamoa.modules;

public class TKIntegerRange implements TKRange<Integer> {
    protected Integer start;
    protected Integer end;

    public TKIntegerRange(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer getStart() {
        return this.start;
    }

    @Override
    public Integer getEnd() {
        return this.end;
    }

    @Override
    public boolean contain(Integer value) throws NullPointerException {
        if (value == null) {
            throw new NullPointerException("value is null.");
        }

        return this.start <= value && this.end >= value;
    }
}
