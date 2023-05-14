package com.ahsan.file.app.repository;

import com.ahsan.file.app.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntitiesByEmail(String email);
}
