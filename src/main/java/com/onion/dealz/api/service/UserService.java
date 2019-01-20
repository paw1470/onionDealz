package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    public List<User> getAllUsers();
    public User getByUserId(Long id);
    public List<User> getAllByName(String name);

    public void create(User user);
    public void delete(Long id);
    public void update(User user);

    public User getByUserName(String name);

    List<Comment> getComments(User user);

}
