package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.CommentDtoConverter;
import com.onion.dealz.api.model.converter.PromotionDtoConverter;
import com.onion.dealz.api.model.converter.UserDtoConverter;
import com.onion.dealz.api.model.dto.*;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
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

    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private CommentDtoConverter commentDtoConverter = new CommentDtoConverter();
    private PromotionDtoConverter promotionDtoConverter = new PromotionDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> all(){
        List<User> users = userService.getAllUsers();
        System.out.println("Users from DB: "+ users.size());
        List<UserDto> userDtos = userDtoConverter.entityToDtoList(users);
        return userDtos;
    }

    @GetMapping("/allByName")
    @ResponseStatus(value = HttpStatus.OK)
    List<UserDto> allByName(@RequestParam("name") String name){
        List<User> users = userService.getAllByName(name);
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
        if(user != null){
            UserDto userDto = userDtoConverter.entityToDto(user);
            return userDto;
        }
        return null;
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteUser(@PathVariable("name") String name){
        System.out.println("User deleted by name: "+ name);
        userService.delete(userService.getByUserName(name));
    }

    @PutMapping("/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    UserDto updateUser(@RequestBody UserDto userDto, @PathVariable("name") String name){
        User user = userService.getByUserName(name);
        user.update(userDto);
        UserDto userDtoNew = userDtoConverter.entityToDto(userService.getByUserName(name));
        return userDtoNew;
    }

    @PutMapping("/{name}/password")
    @ResponseStatus(value = HttpStatus.OK)
    void updateUserPass(@RequestBody UserPasswordDto userPasswordDto, @PathVariable("name") String name){
        User user = userService.getByUserName(name);
        user.updatePassword(userPasswordDto);       //bool
    }

    @GetMapping("/{name}/comments")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> getComments(@PathVariable("name") String name){
        System.out.println("Get comments for User: " + name);
        User user = userService.getByUserName(name);
        List<Comment> comments = userService.getComments( user);
        System.out.println("Get size comments for User: " + comments.size());
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        System.out.println("Get size commentsDto for User: " + commentDtos.size());
        return commentDtos;
    }

    @GetMapping("/{name}/promotions")
    @ResponseStatus(value = HttpStatus.OK)
    List<PromotionDto> getPromotions(@PathVariable("name") String name){
        System.out.println("Get promotions for User: " + name);
        User user = userService.getByUserName(name);
        System.out.println("Get promotions for User: " + user);
        List<Promotion> promotions = userService.getPromotions(user);
        System.out.println("Get size promotions for User: " + promotions.size());
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotions);
        System.out.println("Get size promotionsDto for User: " + promotionDtos.size());
        return promotionDtos;
    }

}
