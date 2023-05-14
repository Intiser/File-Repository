package com.ahsan.file.app.model.resquest;

import com.ahsan.file.app.data.entity.FileEntity;
import lombok.Data;

@Data
public class FileRelatedRequest {
    private long id;
    private FileEntity file;
    private String title;
    private String description;
    private Boolean isResolved;
}
