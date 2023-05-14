package com.ahsan.file.app.repository;

import com.ahsan.file.app.data.entity.FileCheckSum;
import org.springframework.data.repository.CrudRepository;

public interface FileCheckSumRepository extends CrudRepository<FileCheckSum, Long> {
    FileCheckSum findByDownloadlink(String downloadlink);
}
