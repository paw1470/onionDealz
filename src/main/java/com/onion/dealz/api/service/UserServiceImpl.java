package com.onion.dealz.api.service;

import com.onion.dealz.api.exception.UserAlreadyExistsException;
import com.onion.dealz.api.exception.UserNotFoundException;
import com.onion.dealz.api.model.converter.UserDtoConverter;
import com.onion.dealz.api.model.dto.*;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PromotionService promotionService;

    private UserDtoConverter userDtoConverter = new UserDtoConverter();

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAllUsers();
        List<UserDto> userDtos = userDtoConverter.entityToDtoList(users);
        return userDtos;
    }

    @Override
    public UserDto getByUserId(Long id) {
        User user = getByUserIdEntity(id);
        UserDto userDtos = userDtoConverter.entityToDto(user);
        return userDtos;
    }

    @Override
    public List<UserDto> getAllByName(String name) {
        List<User> users = userDao.findAllByName(name);
        List<UserDto> userDtos = userDtoConverter.entityToDtoList(users);
        return userDtos;
    }

    @Override
    public UserDto create(UserRegistrationDto userRegistrationDto) {
        User userTest = userDao.findByName(userRegistrationDto.getLogin());
        if(userTest != null)
            throw new UserAlreadyExistsException();
        User user = userDtoConverter.dtoRegistrationToEntity(userRegistrationDto);
        user.clearLVL();
        userDao.create(user);
        UserDto userDto = userDtoConverter.entityToDto(userDao.findById(user.getId()));
        return userDto;
    }

    @Override
    public void delete(Long id) {
        User user = getByUserIdEntity(id);
        userDao.deleteUser(user);
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        User user = getByUserIdEntity(id);
        user.update(userDto);
        userDao.updateUser(user);
        UserDto userDtoNew = userDtoConverter.entityToDto(userDao.findById(id));
        return userDtoNew;
    }

    @Override
    public UserDto updatePassword(Long id, UserPasswordDto userPasswordDto) {
        User user = getByUserIdEntity(id);
        user.updatePassword(userPasswordDto);
        userDao.updateUser(user);
        UserDto userDtoNew = userDtoConverter.entityToDto(userDao.findById(id));
        return userDtoNew;
    }


    @Override
    public UserDto getByUserName(String name) {
        User user = userDao.findByName(name);
        if(user == null)
            throw new UserNotFoundException();
        UserDto userDto = userDtoConverter.entityToDto(user);
        return userDto;
    }

    @Override
    public List<CommentDto> getComments(String name) {
        User user = userDao.findByName(name);
        List<CommentDto> comments = commentService.getAllByUserId(user.getId());
        return comments;
    }

    @Override
    public List<PromotionDto> getPromotions(String name) {
        User user = userDao.findByName(name);
        List<PromotionDto> promotions = promotionService.getAllByUserId(user.getId());
        return promotions;
    }

    @Override
    public User getByUserIdEntity(Long id) {
        User user = userDao.findById(id);
        if(user == null)
            throw new UserNotFoundException();
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        return userDao.findByName(name);
//    }
}
