package com.ahsan.file.app.model.response;

import com.ahsan.file.app.data.entity.FileEntity;
import lombok.Data;

@Data
public class RequestInfo {
    private long id;
    private FileInfo file;
    private String title;
    private String description;
    private Boolean isResolved;
}
