package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.UserDtoConverter;
import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserDtoConverter userDtoConverter = new UserDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> all(){
        List<User> users = userService.getAllUsers();
        System.out.println("!!!!!!!!"+ users.size());
        List<UserDto> userDtos = userDtoConverter.entityToDtoList(users);
        return userDtos;
    }

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    UserDto create(@RequestBody UserDto userDto){
        System.out.println("!!!!!!!!"+ userDto);
        User user = userDtoConverter.dtoToEntity(userDto);
        user.clearLVL();
        userService.create(user);
        return userDto;
    }

}
