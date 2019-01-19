package com.onion.dealz.api.service;

import com.onion.dealz.api.model.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getByUserId(Long id);
    public List<User> getAllByName(String name);

    public void create(User user);
    public void delete(User user);
    public void update(User user);
}
