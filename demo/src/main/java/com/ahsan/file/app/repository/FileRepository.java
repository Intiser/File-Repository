package com.ahsan.file.app.repository;

import com.ahsan.file.app.data.entity.FileCheckSum;
import com.ahsan.file.app.data.entity.FileEntity;
import com.ahsan.file.app.data.entity.UserEntity;
import com.ahsan.file.app.repository.file.FileInfoDB;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
    List<FileInfoDB> findAllBy();
    FileEntity findByFileCheckSum(FileCheckSum fileCheckSum);

    FileEntity findById(long id);
}
