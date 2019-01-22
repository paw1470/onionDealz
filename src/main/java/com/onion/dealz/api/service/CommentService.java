package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.dto.CommentUpdateDto;

import java.util.List;

public interface CommentService {

    public List<CommentDto> getAllComments();
    public CommentDto getByCommentId(Long id);
    public List<CommentDto> getAllByUserId(Long id);
    public List<CommentDto> getAllByPromotionId(Long id);


    public CommentDto create(CommentDto comment);
    public void delete(Long id);
    public CommentDto update(Long id, CommentUpdateDto commentUpdateDto);

    public void addLike(Long id);
    public void removeLike(Long id);

}
