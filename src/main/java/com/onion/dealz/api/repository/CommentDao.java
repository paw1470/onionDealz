package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Comment;

import java.util.List;


public interface CommentDao {
    List<Comment> findAllComments();

    Comment findById(Long id);

    List<Comment> findAllByUserId(Long id);

    List<Comment> findAllByPromotionId(Long id);

//    List<User> findAllLikes(Long id);

    void addComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);
}
