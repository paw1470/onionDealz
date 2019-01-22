package com.onion.dealz.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private UserDto user;
    private PromotionDto promotion;
    private String text;
    private int likes;
    private String addDate;
    private String modifyDate;
}
