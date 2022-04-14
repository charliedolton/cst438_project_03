package com.project3.easel.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUserId(Integer userId);
    User findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
}
