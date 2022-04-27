package com.example.easel_new.database;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUserId(Integer userId);
    User findUserByUsername(String username);
    Boolean existsUserByUsername(String username);
}