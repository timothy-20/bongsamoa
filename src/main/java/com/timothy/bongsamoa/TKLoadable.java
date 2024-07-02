package com.timothy.bongsamoa;

import java.io.File;

interface TKLoadable {
    void save() throws Exception;
    void saveAs(File newFile) throws Exception;
}
