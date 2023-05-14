package com.ahsan.file.app.repository;

import com.ahsan.file.app.data.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<RequestEntity, Long> {
    List<RequestEntity> findByIsResolvedIsFalse();
}
