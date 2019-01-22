package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.Shop;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionDtoConverter {
    private UserDtoConverter userDtoConverter = new UserDtoConverter();
    private ShopDtoConverter shopDtoConverter = new ShopDtoConverter();
    private DateUtils dateUtils = new DateUtils();

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
        promotionDto.setAddDate(dateUtils.dateToString(promotion.getAddDate()));
        if(promotion.getModifyDate() != null)
            promotionDto.setModifyDate(dateUtils.dateToString(promotion.getModifyDate()));
        if(promotion.getStartDate() != null)
            promotionDto.setStartDate(dateUtils.dateToString(promotion.getStartDate()));
        if(promotion.getEndDate() != null)
            promotionDto.setEndDate(dateUtils.dateToString(promotion.getEndDate()));
        promotionDto.setLikes(promotion.getLikes().size());
        promotionDto.setUnlikes(promotion.getUnlikes().size());
        promotionDto.setActive(promotion.isActive());
        promotionDto.setLocal(promotion.isLocal());
        promotionDto.setShop(shopDtoConverter.entityToDto(promotion.getShop()));
        promotionDto.setShopAddress(promotion.getShopAddress());
        promotionDto.setPhoto(promotion.getPhoto());
        return promotionDto;
    }

    public Promotion dtoAddToEntity(PromotionDto promotionDto, User user, Shop shop){
        Promotion promotion = new Promotion();
        promotion.setTitle(promotionDto.getTitle());
        promotion.setDescription(promotionDto.getDescription());
        promotion.setPrice(promotionDto.getPrice());
        promotion.setRegularPrice(promotionDto.getRegularPrice());
        promotion.setShippingPrice(promotionDto.getShippingPrice());
        promotion.setCupon(promotionDto.getCupon());
        promotion.setLink(promotionDto.getLink());
        promotion.setUser(user);
        promotion.setAddDate(dateUtils.getCurrentDateTime());
        promotion.setStartDate(dateUtils.stringToDate(promotionDto.getStartDate()));
        promotion.setEndDate(dateUtils.stringToDate(promotionDto.getEndDate()));
        promotion.setLikes(new ArrayList<>());
        promotion.setUnlikes(new ArrayList<>());
        promotion.setObservers(new ArrayList<>());
        promotion.setActive(promotionDto.isActive());
        promotion.setLocal(promotionDto.isLocal());
        promotion.setShop(shop);
        promotion.setShopAddress(promotionDto.getShopAddress());
        promotion.setPhoto(promotionDto.getPhoto());
        return promotion;
    }

    public List<PromotionDto> entityToDtoList(List<Promotion> promotions){
        List<PromotionDto> promotionDtos = new ArrayList();
        for(Promotion p:promotions){
            promotionDtos.add(entityToDto(p));
        }
        return promotionDtos;
    }
}
