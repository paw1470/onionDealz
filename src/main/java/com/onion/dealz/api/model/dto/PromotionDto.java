package com.onion.dealz.api.model.dto;

import com.onion.dealz.api.repository.ShopDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private double regularPrice;
    private double shippingPrice;
    private String cupon;
    private String link;
    private UserDto user;
    private Date addDate;
    private Date modifyDate;
    private Date startDate;
    private Date endDate;
    private int likes;
    private int unlikes;
    private boolean isActive;
    private boolean isLocal;
    private ShopDto shop;
    private String shopAddress;
    private String photo;
}
