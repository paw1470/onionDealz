package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.dto.ShopDto;
import com.onion.dealz.api.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<ShopDto> all(){
        List<ShopDto> shopDtos = shopService.getAllShops();
        return shopDtos;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    ShopDto create(@RequestBody ShopDto shopDto){
        ShopDto shopDtoNew = shopService.create(shopDto);
        return shopDtoNew;
    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteShop(@PathVariable("id") Long id){
        shopService.delete(id);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(value = HttpStatus.OK)
    ShopDto updateShop(@RequestBody ShopDto shopDto, @PathVariable("id") Long id){
        ShopDto shopDtoNew = shopService.update(id, shopDto);
        return shopDtoNew;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ShopDto findById(@PathVariable("id") Long id){
        ShopDto shopDto = shopService.getByShopId(id);
        return shopDto;
    }
}
