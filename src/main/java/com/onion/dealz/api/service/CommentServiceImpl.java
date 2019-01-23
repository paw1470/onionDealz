package com.onion.dealz.api.service;

import com.onion.dealz.api.exception.ResourceNotFoundException;
import com.onion.dealz.api.model.converter.CommentDtoConverter;
import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userService;

    CommentDtoConverter commentDtoConverter = new CommentDtoConverter();


    @Cacheable
    @Override
    public List<CommentDto> getAllComments(){
        List<Comment> comments = this.commentDao.findAllComments();
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

    @Cacheable
    @Override
    public CommentDto getByCommentId(Long id){
        Comment comment = getCommentByIdEntity(id);
        CommentDto commentDtoNew = commentDtoConverter.entityToDto(comment);
        return commentDtoNew;
    }

    @Cacheable
    @Override
    public List<CommentDto> getAllByUserId(Long id){
        List<Comment> comments = commentDao.findAllByUserId(id);
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

    @Override
    public List<CommentDto> getAllByPromotionId(Long id) {
        List<Comment> comments = commentDao.findAllByPromotionId(id);
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

    @Override
    public CommentDto create(CommentDto commentDto){  //todo dodac usera jak vedzie autoryzacja
        Promotion promotion = promotionService.getPromotionByIdEntity(commentDto.getPromotion().getId());
        User user = userService.getByUserIdEntity(commentDto.getUser().getId()); //todo dodac usera jak bedzie autoryzacja
        Comment comment = commentDtoConverter.dtoAddToEntity(commentDto, user, promotion);
        commentDao.addComment(comment);
        CommentDto commentDtoNew = commentDtoConverter.entityToDto(commentDao.findById(comment.getId()));
        return commentDtoNew;
    }

    @Override
    public void delete(Long id) {
        Comment comment = getCommentByIdEntity(id);
        commentDao.deleteComment(comment);
    }

    @Override
    public CommentDto update(Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment = getCommentByIdEntity(id);
        comment.update(commentUpdateDto);
        commentDao.updateComment(comment);
        CommentDto commentDtoNew = commentDtoConverter.entityToDto(commentDao.findById(comment.getId()));
        return commentDtoNew;
    }

    @Override
    public void addLike(Long commentId, Long userId) {
        User user = userService.getByUserIdEntity(userId); //todo dodac usera jak bedzie autoryzacja
        Comment comment = getCommentByIdEntity(commentId);
        comment.addLike(user);
        commentDao.updateComment(comment);
    }

    @Override
    public void removeLike(Long commentId, Long userId) {
        User user = userService.getByUserIdEntity(userId); //todo dodac usera jak bedzie autoryzacja
        Comment comment = getCommentByIdEntity(commentId);
        comment.removeLike(user);
        commentDao.updateComment(comment);
    }

    @Override
    public Comment getCommentByIdEntity(Long id) {
        Comment comment = commentDao.findById(id);
        if(comment == null){
            throw new ResourceNotFoundException();
        }
        return comment;
    }

}
