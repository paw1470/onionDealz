package com.onion.dealz.api.service;

import com.onion.dealz.api.model.entity.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> getAllComments();
    public Comment getByCommentId(Long id);
    public List<Comment> getAllByUserId(Long id);
    public void create(Comment comment);
    public void delete(Comment comment);
    public void update(Comment comment);

}
