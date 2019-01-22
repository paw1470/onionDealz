package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.dto.UserRegistrationDto;
import com.onion.dealz.api.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {
    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setLevel(user.getLevel());
        userDto.setDescription(user.getDescription());
        userDto.setPhoto(user.getPhoto());
        return userDto;
    }

    public User dtoRegistrationToEntity(UserRegistrationDto userRegistrationDto){
        User user = new User();
        user.setLogin(userRegistrationDto.getLogin());
        user.setPassword(userRegistrationDto.getPassword());
        user.setDescription(userRegistrationDto.getDescription());
        user.setPhoto(userRegistrationDto.getPhoto());
        return user;
    }

    public List<UserDto> entityToDtoList(List<User> users){
        List<UserDto> userDtos = new ArrayList();
        for(User u:users){
            userDtos.add(entityToDto(u));
        }
        return userDtos;
    }
}
