package com.onion.dealz.api.controller;


import com.onion.dealz.api.model.dto.*;
import com.onion.dealz.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> all(){
        List<UserDto> userDtos = userService.getAllUsers();
        return userDtos;
    }

    @GetMapping("/allByName")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> allByName(@RequestParam("name") String name){
        List<UserDto> userDtos = userService.getAllByName(name);
        return userDtos;
    }

    @GetMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto getByName(@PathVariable("name") String name){
        UserDto userDto = userService.getByUserName(name);
        return userDto;
    }

    @PostMapping("/auth/registration")
    @ResponseStatus(value = HttpStatus.CREATED)
    UserDto create(@RequestBody UserRegistrationDto userRegistrationDto){
        UserDto userDto = userService.create(userRegistrationDto);
        return userDto;
    }

    @DeleteMapping("/auth/{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
    }

    @PutMapping("/auth/{id}/update")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long id){
       UserDto userDtoNew = userService.update(id, userDto);            //todo
       return userDtoNew;
    }

    @PutMapping("/auth/{id}/password")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    UserDto updateUserPass(@RequestBody UserPasswordDto userPasswordDto, @PathVariable("id") Long id){
        UserDto userDto = userService.updatePassword(id, userPasswordDto);
        return userDto;
    }

    @GetMapping("/{name}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> getComments(@PathVariable("name") String name){
        List<CommentDto> commentDtos = userService.getComments(name);
        return commentDtos;
    }

    @GetMapping("/{name}/promotions")
    @ResponseStatus(value = HttpStatus.OK)
    List<PromotionDto> getPromotions(@PathVariable("name") String name){
        List<PromotionDto> promotionDtos = userService.getPromotions(name);
        return promotionDtos;
    }

}
