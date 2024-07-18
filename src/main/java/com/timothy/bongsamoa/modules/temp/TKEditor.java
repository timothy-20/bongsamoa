package com.timothy.bongsamoa.modules.temp;

import java.io.Closeable;
import java.io.IOException;

public interface TKEditor extends Closeable {
    void create(String filePath) throws Exception ;
    void open(String filePath) throws Exception;
    void save() throws Exception;
    void saveAs(String filePath) throws Exception;
    TKEditorAccessor edit();
    void restore() throws Exception;
    void restoreAs(int key) throws Exception;
}