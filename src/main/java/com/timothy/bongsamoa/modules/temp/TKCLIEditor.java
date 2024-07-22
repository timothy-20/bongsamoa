package com.timothy.bongsamoa.modules.temp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class TKCLIEditor extends TKTextEditor {
    private final TKCLIEditorAccessor accessor;

    public TKCLIEditor() {
        this.accessor = new TKCLIEditorAccessor(this.getFileChannel());
    }

    @Override
    public TKTextEditorAccessor edit() {
        return null;
    }

    @Override
    public TKTextCursor getCursor() {
        return null;
    }

    @Override
    public String getContent() throws IOException {
        return this.accessor.readAll();
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}

//public class TKCLIEditor implements TKTextEditor {
//
//
//    public TKCLIEditor() {
//
//    }
//
//    @Override
//    public void open(String filePath) {
//
//    }
//
//    @Override
//    public void save() {
//
//    }
//
//    @Override
//    public void saveAs(String filePath) {
//
//    }
//
//    @Override
//    public void restore() {
//
//    }
//
//    @Override
//    public void restoreAs(int key) {
//
//    }
//
//    @Override
//    public TKTextEditorAccessor edit() {
//        return null;
//    }
//
//    @Override
//    public TKTextCursor getCursor() {
//        return null;
//    }
//
//    @Override
//    public String getContent() {
//        return "";
//    }
//
//    @Override
//    public void undo() {
//
//    }
//
//    @Override
//    public void redo() {
//
//    }
//
//    @Override
//    public void close() throws IOException {
//
//    }
//}
