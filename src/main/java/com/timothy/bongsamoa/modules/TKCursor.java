package com.timothy.bongsamoa.modules;

public interface TKCursor {
    TKCursorPosition getPosition();
    void movePrev();
    void moveNext();
    void moveFront();
    void moveBack();
}

interface TKCursorPosition {

}