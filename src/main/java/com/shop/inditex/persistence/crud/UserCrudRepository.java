package com.shop.inditex.persistence.crud;

import com.shop.inditex.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<UserEntity, String> {
}
