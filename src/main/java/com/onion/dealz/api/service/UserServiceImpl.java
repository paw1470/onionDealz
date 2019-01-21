package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PromotionService promotionService;


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

    @Override
    public User getByUserName(String name) {
        return this.userDao.findByName(name);
    }

    @Override
    public List<Comment> getComments(User user) {
        return this.commentService.getAllByUserId(user.getId());
    }

    @Override
    public List<Promotion> getPromotions(User user) {
        return this.promotionService.getAllByUserId(user.getId());
    }
}
