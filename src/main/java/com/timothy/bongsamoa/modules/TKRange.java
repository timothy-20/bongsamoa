package com.timothy.bongsamoa.modules;

public interface TKRange<T extends Comparable<T>>{
    T getStart();
    T getEnd();
    boolean contain(T value);
}
