package com.timothy.bongsamoa.modules;

public interface TKCursor<T> {
    T getPosition();
    void moveTo(T position);
    void movePrev();
    void moveNext();
    void moveFront();
    void moveBack();
}
