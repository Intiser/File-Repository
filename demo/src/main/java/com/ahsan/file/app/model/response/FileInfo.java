package com.ahsan.file.app.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class FileInfo {
    private long id;
    private String name;
    private long size;
    private String downloadLink;
    private String userName;
    private String fileType;
    private boolean isBlocked;
    private LocalDateTime dateTime;
}
