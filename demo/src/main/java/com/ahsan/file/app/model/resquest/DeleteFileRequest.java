package com.ahsan.file.app.model.resquest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteFileRequest {
    private long id;
    private String name;
    private long size;
    private String downloadLink;
    private String userName;
    private String fileType;
}
