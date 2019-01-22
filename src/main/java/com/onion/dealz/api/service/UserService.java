package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.*;
import com.onion.dealz.api.model.entity.User;

import java.util.List;

public interface UserService {
    public List<UserDto> getAllUsers();
    public UserDto getByUserId(Long id);
    public List<UserDto> getAllByName(String name);

    public UserDto create(UserRegistrationDto userRegistrationDto);
    public void delete(Long id);
    public UserDto update(Long id, UserDto userDto);

    public UserDto getByUserName(String name);

    public List<CommentDto> getComments(String name);

    public List<PromotionDto> getPromotions(String name);

    public UserDto updatePassword(Long id, UserPasswordDto userPasswordDto);

    public User getByUserIdEntity(Long id);
}
