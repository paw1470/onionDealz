package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDtoConverter {
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private PromotionDtoConverter promotionDtoConverter = new PromotionDtoConverter();

    public CommentDto entityToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUser(userDtoConverter.entityToDto(comment.getUser()));
        commentDto.setPromotion(promotionDtoConverter.entityToDto(comment.getPromotion()));
        commentDto.setText(comment.getText());
        commentDto.setLikes(userDtoConverter.entityToDtoList((List<User>) comment.getLikes()));
        commentDto.setAddDate(comment.getAddDate());
        commentDto.setModifyDate(comment.getModifyDate());
        return commentDto;
    }

    public Comment dtoAddToEntity(CommentDto commentDto, User user, Promotion promotion, Date date){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setUser(user);
        comment.setPromotion(promotion);
        comment.setText(commentDto.getText());
        comment.setAddDate(date);
        return comment;
    }

    public List<CommentDto> entityToDtoList(List<Comment> comments){
        List<CommentDto> commentDtos = new ArrayList();
        for(Comment c:comments){
            commentDtos.add(entityToDto(c));
        }
        return commentDtos;
    }
}
