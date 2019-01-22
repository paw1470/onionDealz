package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.PromotionDtoConverter;
import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.service.PromotionService;
import com.onion.dealz.api.service.ShopService;
import com.onion.dealz.api.service.UserService;
import com.onion.dealz.api.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        List<PromotionDto> promotionDtos = promotionService.getAllPromotions();
        return promotionDtos;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    PromotionDto create(@RequestBody PromotionDto promotionDto){
        PromotionDto promotionDtoNew = promotionService.create(promotionDto);
        return promotionDtoNew;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    PromotionDto getById(@PathVariable("id") Long id){
        PromotionDto promotionDto = promotionService.getPromotionById(id);
        return promotionDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deletePromotion(@PathVariable("id") Long id){
        promotionService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    PromotionDto updatePromotion(@RequestBody PromotionDto promotionDto, @PathVariable("id") Long id ){
        PromotionDto promotionDtoNew = promotionService.update(id, promotionDto);
        return promotionDtoNew;
    }

    @PutMapping("/{id}/like")
    @ResponseStatus(value = HttpStatus.OK)
    void addLike(@PathVariable("id") Long id ){
        promotionService.addLike(id);
    }

    @PutMapping("/{id}/removelike")
    @ResponseStatus(value = HttpStatus.OK)
    void removeLink(@PathVariable("id") Long id ){
        promotionService.removeLike(id);
    }

    @PutMapping("/{id}/unlike")
    @ResponseStatus(value = HttpStatus.OK)
    void addUnlike(@PathVariable("id") Long id ){
        promotionService.addUnlike(id);
    }

    @PutMapping("/{id}/removeunlike")
    @ResponseStatus(value = HttpStatus.OK)
    void removeUnlike(@PathVariable("id") Long id ){
        promotionService.removeUnlike(id);
    }


    }
