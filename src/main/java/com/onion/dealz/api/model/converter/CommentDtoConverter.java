package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentDtoConverter {
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private PromotionDtoConverter promotionDtoConverter = new PromotionDtoConverter();
    private DateUtils dateUtils = new DateUtils();

    public CommentDto entityToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUser(userDtoConverter.entityToDto(comment.getUser()));
        commentDto.setPromotion(promotionDtoConverter.entityToDto(comment.getPromotion()));
        commentDto.setText(comment.getText());
        commentDto.setLikes(comment.getLikes().size());
        commentDto.setAddDate(dateUtils.dateToString(comment.getAddDate()));
        if(comment.getModifyDate() != null)
            commentDto.setModifyDate(dateUtils.dateToString(comment.getModifyDate()));
        return commentDto;
    }

    public Comment dtoAddToEntity(CommentDto commentDto, User user, Promotion promotion){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setUser(user);
        comment.setPromotion(promotion);
        comment.setText(commentDto.getText());
        comment.setAddDate(dateUtils.getCurrentDateTime());
        comment.setLikes(new ArrayList<>());
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
