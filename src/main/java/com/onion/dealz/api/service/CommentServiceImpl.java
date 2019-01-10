package com.onion.dealz.api.service;

import com.google.common.collect.Lists;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;
    private PromotionService promotionService;

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
    public void create(Comment comment){
        commentDao.addComment(comment);

    }

    @Override
    public void delete(Comment comment) {
        commentDao.deleteComment(comment);
    }

    @Override
    public void update(Comment comment) {
        commentDao.updateComment(comment);
    }


}
