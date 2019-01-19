package com.onion.dealz.api.service;

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


    @Override
    public List<User> getAllUsers() {
        return this.userDao.findAllUsers();
    }

    @Override
    public User getByUserId(Long id) {
        return this.userDao.findById(id);
    }

    @Override
    public List<User> getAllByName(String name) {
        return this.userDao.findAllByName(name);
    }

    @Override
    public void create(User user) {
        this.userDao.create(user);
    }

    @Override
    public void delete(User user) {
        this.userDao.deleteUser(user);
    }

    @Override
    public void update(User user) {
        this.userDao.updateUser(user);

    }
}
