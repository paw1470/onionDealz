package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.User;

import java.util.List;

public interface CommentService {

    public List<Comment> getAllComments();
    public Comment getByCommentId(Long id);
    public List<Comment> getAllByUserId(Long id);
    public List<Comment> getAllByPromotionId(Long id);


    public void create(Comment comment);
    public void delete(Comment comment);
    public void update(CommentUpdateDto comment);

    public void addLike(Comment comment, User user);
    public void removeLike(Comment comment, User user);

}
