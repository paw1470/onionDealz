package com.onion.dealz.api.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentUpdateDto {

    private Long id;

    private String text;

    private String modifyDate;
}
