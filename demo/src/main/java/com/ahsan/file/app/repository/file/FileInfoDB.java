package com.ahsan.file.app.repository.file;

import com.ahsan.file.app.data.entity.FileCheckSum;

public interface FileInfoDB {
    Long getId();
    String getName();
    Long getSize();
    FileCheckSum getFileCheckSum();
}
