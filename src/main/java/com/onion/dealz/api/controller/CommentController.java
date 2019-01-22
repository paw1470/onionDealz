package com.onion.dealz.api.controller;


import com.onion.dealz.api.model.converter.CommentDtoConverter;
import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.service.CommentService;
import com.onion.dealz.api.service.PromotionService;
import com.onion.dealz.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PromotionService promotionService;

    private CommentDtoConverter commentDtoConverter = new CommentDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> all(){
        System.out.println("111111111111111");
        List<Comment> comments= commentService.getAllComments();
        System.out.println("22222222222");
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        System.out.println("33333333333333");
        return commentDtos;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    CommentDto create(@RequestBody CommentDto commentDto){
        if(commentDto.getPromotion() != null && commentDto.getPromotion().getId() != null){
            Promotion promotion = promotionService.getByPromotionId(commentDto.getPromotion().getId());
            User user = userService.getByUserId(Long.valueOf(1)); //todo dodac usera jak bedzie autoryzacja
            Comment comment = commentDtoConverter.dtoAddToEntity(commentDto, user, promotion);
            commentService.create(comment);
            CommentDto commentDtoNew = commentDtoConverter.entityToDto(commentService.getByCommentId(comment.getId()));
            return commentDtoNew;
        }
        return null;
    }

    @GetMapping("/allByPromotion")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> allByPromotion(@RequestParam("id") Long id){
        List<Comment> comments = commentService.getAllByPromotionId(id);
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

    @GetMapping("/allByUser")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> allByUser(@RequestParam("id") Long id){
        List<Comment> comments = commentService.getAllByUserId(id);
        List<CommentDto> commentDtos = commentDtoConverter.entityToDtoList(comments);
        return commentDtos;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteComment(@PathVariable("id") Long id){
        commentService.delete(commentService.getByCommentId(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    CommentDto updateComment(@RequestBody CommentUpdateDto commentDto, @PathVariable("id") Long id){
        Comment comment = commentService.getByCommentId(id);
        comment.update(commentDto);
        commentService.update(comment);
        CommentDto commentDtoNew = commentDtoConverter.entityToDto(commentService.getByCommentId(id));
        return commentDtoNew;
    }
}
