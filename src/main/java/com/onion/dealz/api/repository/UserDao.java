package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface UserDao{
    List<User> findAllUsers();

    User findById(Long id);

    List<User> findAllByName(String name);

    void create(User user);

    void deleteUser(Long id);

    void updateUser(User user);

    User findByName(String name);
}
