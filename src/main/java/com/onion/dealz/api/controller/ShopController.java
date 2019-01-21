package com.onion.dealz.api.controller;

import com.onion.dealz.api.model.converter.ShopDtoConverter;
import com.onion.dealz.api.model.dto.ShopAddDto;
import com.onion.dealz.api.model.dto.ShopDto;
import com.onion.dealz.api.model.entity.Shop;
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

    private ShopDtoConverter shopDtoConverter = new ShopDtoConverter();

    @GetMapping("/all")
    @ResponseStatus(value = HttpStatus.OK)
    List<ShopDto> all(){
        List<Shop> shops = shopService.getAllShops();
        System.out.println("Shops from DB: "+ shops.size());
        List<ShopDto> shopDtos = shopDtoConverter.entityToDtoList(shops);
        return shopDtos;
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    ShopDto create(@RequestBody ShopDto shopDto){
        System.out.println("Shop add: "+ shopDto);
        Shop shop = shopDtoConverter.dtoAddToEntity(shopDto);
        shopService.create(shop);
        ShopDto shopDtoNew = shopDtoConverter.entityToDto(shopService.getByShopId(shop.getId()));
        return shopDtoNew;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void deleteShop(@PathVariable("id") Long id){
        System.out.println("Shop deleted by id: "+ id);
        shopService.delete(shopService.getByShopId(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ShopDto updateShop(@RequestBody ShopDto shopDto, @PathVariable("id") Long id){
        Shop shop = shopService.getByShopId(id);
        shop.update(shopDto);
        ShopDto shopDtoNew = shopDtoConverter.entityToDto(shopService.getByShopId(id));
        return shopDtoNew;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    ShopDto findById(@PathVariable("id") Long id){
        System.out.println("Shop by id: "+ id);
        Shop shop = shopService.getByShopId(id);
        if(shop != null){
            ShopDto shopDto = shopDtoConverter.entityToDto(shop);
            return shopDto;
        }
        return null;
    }
}
