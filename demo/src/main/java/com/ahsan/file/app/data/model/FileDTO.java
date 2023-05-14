package com.ahsan.file.app.data.model;

import com.ahsan.file.app.data.entity.FileCheckSum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {

    private long id;
    private String name;
    private byte[] data;
    private long size;
    private FileCheckSum fileCheckSum;
    private String fileType;
    private boolean isBlocked;
    private LocalDateTime dateTime;


}
