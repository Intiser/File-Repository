package com.ahsan.file.app.model.response;

import com.ahsan.file.app.data.entity.FileCheckSum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class FileUploadResponse{
    String downloadLink;
    private long id;
    private String name;
    private long size;
    private FileCheckSum fileCheckSum;
    private String fileType;
    private boolean isBlocked;
    private LocalDateTime dateTime;
}
