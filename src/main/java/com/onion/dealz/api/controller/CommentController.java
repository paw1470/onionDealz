package com.onion.dealz.api.controller;


import com.onion.dealz.api.model.dto.CommentDto;
import com.onion.dealz.api.model.dto.CommentUpdateDto;
import com.onion.dealz.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> all(){
        List<CommentDto> commentDtos = commentService.getAllComments();
        return commentDtos;
    }

    @GetMapping("/allByPromotion")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> allByPromotion(@RequestParam("id") Long id){
        List<CommentDto> commentDtos = commentService.getAllByPromotionId(id);
        return commentDtos;
    }

    @GetMapping("/allByUser")
    @ResponseStatus(value = HttpStatus.OK)
    List<CommentDto> allByUser(@RequestParam("id") Long id){
        List<CommentDto> commentDtos = commentService.getAllByUserId(id);
        return commentDtos;
    }

    @GetMapping("/{commentId}")
    @ResponseStatus(value = HttpStatus.OK)
    CommentDto getById(@PathVariable("commentId") Long commentId){
        CommentDto commentDto = commentService.getByCommentId(commentId);
        return commentDto;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    CommentDto create(@RequestBody CommentDto commentDto){
        CommentDto commentDtoNew = commentService.create(commentDto);
        return commentDtoNew;
    }

    @DeleteMapping("/{commentId}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.delete(commentId);
    }

    @PutMapping("/{commentId}/update")
    @ResponseStatus(value = HttpStatus.OK)
    CommentDto updateComment(@RequestBody CommentUpdateDto commentUpdateDto, @PathVariable("commentId") Long commentId){
        CommentDto commentDtoNew = commentService.update(commentId, commentUpdateDto);
        return commentDtoNew;
    }

    @PostMapping("/{commentId}/like/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    void like(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId){
        commentService.addLike(commentId, userId);
    }

    @PostMapping("/{commentId}/removelike/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    void removelike(@PathVariable("commentId") Long commentId, @PathVariable("userId") Long userId){
        commentService.removeLike(commentId, userId);
    }
}
