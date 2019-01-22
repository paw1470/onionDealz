package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.PromotionDtoConverter;
import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.Shop;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.service.PromotionService;
import com.onion.dealz.api.service.ShopService;
import com.onion.dealz.api.service.UserService;
import com.onion.dealz.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    private DateUtils dateUtils = new DateUtils();
    private PromotionDtoConverter promotionDtoConverter = new PromotionDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<PromotionDto> all(){
        List<Promotion> promotions = promotionService.getAllPromotions();
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotions);
        return promotionDtos;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    PromotionDto create(@RequestBody PromotionDto promotionDto){
        if(promotionDto.getDescription().length()>5){
            User user = userService.getByUserId(Long.valueOf(1));
            Shop shop = shopService.getByShopId(promotionDto.getShop().getId());
            Promotion promotion = promotionDtoConverter.dtoAddToEntity(promotionDto, user, shop);
            promotionService.create(promotion);
            PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionService.getByPromotionId(promotion.getId()));
            return promotionDtoNew;
        }
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    PromotionDto getById(@PathVariable("id") Long id){
        Promotion promotion = promotionService.getByPromotionId(id);
        if(promotion != null){
            PromotionDto promotionDto = promotionDtoConverter.entityToDto(promotion);
            return promotionDto;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deletePromotion(@PathVariable("id") Long id){
        promotionService.delete(promotionService.getByPromotionId(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    PromotionDto updatePromotion(@RequestBody PromotionDto promotionDto, @PathVariable("id") Long id ){
        Promotion promotion = promotionService.getByPromotionId(id);
        promotion.update(promotionDto);
        promotionService.update(promotion);
        PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionService.getByPromotionId(id));
        return promotionDtoNew;
    }


}
