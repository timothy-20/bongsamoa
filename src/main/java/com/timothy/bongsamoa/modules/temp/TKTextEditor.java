package com.timothy.bongsamoa.modules.temp;

import com.timothy.bongsamoa.modules.TKMutableIntegerRange;

public interface TKTextEditor extends TKEditor {
    @Override
    TKTextEditorAccessor edit();
    TKTextCursor getCursor();
    String getContent();
    void undo();
    void redo();
}



