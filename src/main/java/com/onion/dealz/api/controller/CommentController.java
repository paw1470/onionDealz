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

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    CommentDto getById(@PathVariable("id") Long id){
        CommentDto commentDto = commentService.getByCommentId(id);
        return commentDto;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    CommentDto create(@RequestBody CommentDto commentDto){
        CommentDto commentDtoNew = commentService.create(commentDto);
        return commentDtoNew;
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteComment(@PathVariable("id") Long id){
        commentService.delete(id);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(value = HttpStatus.OK)
    CommentDto updateComment(@RequestBody CommentUpdateDto commentUpdateDto, @PathVariable("id") Long id){
        CommentDto commentDtoNew = commentService.update(id, commentUpdateDto);
        return commentDtoNew;
    }

    @PostMapping("/{id}/like")
    @ResponseStatus(value = HttpStatus.OK)
    void like(@PathVariable("id") Long id){
        commentService.addLike(id);
    }

    @PostMapping("/{id}/removelike")
    @ResponseStatus(value = HttpStatus.OK)
    void removelike(@PathVariable("id") Long id){
        commentService.removeLike(id);
    }
}
