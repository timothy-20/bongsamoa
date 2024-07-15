package com.timothy.bongsamoa.modules.temp;

import java.io.Closeable;

public interface TKEditor extends Closeable {
    void open(String filePath);
    void save();
    void saveAs(String filePath);
    TKEditorAccessor edit();
    void restore();
    void restoreAs(int key);
}