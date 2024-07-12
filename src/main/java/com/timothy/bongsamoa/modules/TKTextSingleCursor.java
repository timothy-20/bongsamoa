package com.timothy.bongsamoa.modules;

public class TKTextSingleCursor extends TKSingleCursor {
    // 1. 데이터를 커서가 직접 가지는 것이 아닌, 오퍼레이터를 합성하여 사용하는 방법
    // 2. 오퍼레이터가 데이터에 직접적으로 접근
    protected StringBuilder stringBuilder;
    protected TKModifiable modifiable;

    public TKTextSingleCursor(CharSequence cs) {
        super();
        this.stringBuilder = new StringBuilder(cs);
        this.position = cs.length();
    }

    @Override
    public Integer getCapacity() {
        return this.stringBuilder.length();
    }
}