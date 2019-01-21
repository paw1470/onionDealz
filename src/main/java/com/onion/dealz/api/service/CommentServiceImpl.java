package com.onion.dealz.api.service;

import com.google.common.collect.Lists;
import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Cacheable
    @Override
    public List<Comment> getAllComments(){
        return this.commentDao.findAllComments();
    }

    @Cacheable
    @Override
    public Comment getByCommentId(Long id){
        return this.commentDao.findById(id);
    }

    @Cacheable
    @Override
    public List<Comment> getAllByUserId(Long id){
        return this.commentDao.findAllByUserId(id);
    }

    @Override
    public List<Comment> getAllByPromotionId(Long id) {
        return this.commentDao.findAllByPromotionId(id);
    }

    @Override
    public void create(Comment comment){
        commentDao.addComment(comment);

    }

    @Override
    public void delete(Comment comment) {
        commentDao.deleteComment(comment);
    }

    @Override
    public void update(CommentUpdateDto comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void addLike(Comment comment, User user) {
        commentDao.addLike(comment, user);
    }

    @Override
    public void removeLike(Comment comment, User user) {
        commentDao.removeLike(comment, user);
    }


}
