package com.ahsan.file.app.data.model;

import lombok.Data;

@Data
public class RequestDTO {
    private long id;
    private FileDTO file;
    private String title;
    private String description;
    private Boolean isResolved;
}
