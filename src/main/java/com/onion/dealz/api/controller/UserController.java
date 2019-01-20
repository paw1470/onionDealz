package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.CommentDtoConverter;
import com.onion.dealz.api.model.converter.UserDtoConverter;
import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.dto.UserRegistrationDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private CommentDtoConverter commentDtoConverter = new CommentDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> all(){
        List<User> users = userService.getAllUsers();
        System.out.println("Users from DB: "+ users.size());
        List<UserDto> userDtos = userDtoConverter.entityToDtoList(users);
        return userDtos;
    }

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    UserRegistrationDto create(@RequestBody UserRegistrationDto userRegistrationDto){
        System.out.println("User registration: "+ userRegistrationDto);
        User user = userDtoConverter.dtoRegistrationToEntity(userRegistrationDto);
        user.clearLVL();
        userService.create(user);
        return userRegistrationDto;
    }

    @GetMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto getByName(@PathVariable("name") String name){
        System.out.println("User by name: "+ name);
        User user = userService.getByUserName(name);
        UserDto userDto = userDtoConverter.entityToDto(user);
        return userDto;

    }

    @DeleteMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteUser(@PathVariable("name") String name){
        System.out.println("User deleted by name: "+ name);
        userService.delete(
                userService.getByUserName(name).getId()
        );
    }

    @GetMapping("/{name}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> getComments(@PathVariable("name") String name){
        System.out.println("Get comments for User: " + name);
        List<Comment> comments = userService.getComments( userService.getByUserName(name));
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

}
