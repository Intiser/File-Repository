package com.ahsan.file.app.service;


import com.ahsan.file.app.data.entity.FileEntity;
import com.ahsan.file.app.data.model.DeleteFileDTO;
import com.ahsan.file.app.data.model.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileDTO storeFile(MultipartFile file);
    List<FileDTO> getFileList();
    FileDTO getFile(String link);

    FileDTO getFile(FileDTO fileDTO);


    DeleteFileDTO deleteFile(long id);
}
