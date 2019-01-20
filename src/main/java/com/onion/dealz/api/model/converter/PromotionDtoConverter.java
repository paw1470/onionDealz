package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.entity.Promotion;

public class PromotionDtoConverter {
    UserDtoConverter userDtoConverter = new UserDtoConverter();
    ShopDtoConverter shopDtoConverter = new ShopDtoConverter();

    public PromotionDto entityToDto(Promotion promotion) {
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(promotion.getId());
        promotionDto.setTitle(promotion.getTitle());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setPrice(promotion.getPrice());
        promotionDto.setRegularPrice(promotion.getRegularPrice());
        promotionDto.setShippingPrice(promotion.getShippingPrice());
        promotionDto.setCupon(promotion.getCupon());
        promotionDto.setLink(promotion.getLink());
        promotionDto.setUser(userDtoConverter.entityToDto(promotion.getUser()));
        promotionDto.setAddDate(promotion.getAddDate());
        promotionDto.setModifyDate(promotion.getModifyDate());
        promotionDto.setStartDate(promotion.getStartDate());
        promotionDto.setEndDate(promotion.getEndDate());
        promotionDto.setLikes(promotion.getLikes().size());
        promotionDto.setUnlikes(promotion.getUnlikes().size());
        promotionDto.setActive(promotion.isActive());
        promotionDto.setLocal(promotion.isLocal());
        promotionDto.setShop(shopDtoConverter.entityToDto(promotion.getShop()));
        promotionDto.setShopAddress(promotion.getShopAddress());
        promotionDto.setPhoto(promotion.getPhoto());
        return promotionDto;
    }
}
