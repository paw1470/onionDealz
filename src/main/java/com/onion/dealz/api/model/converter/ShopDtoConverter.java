package com.onion.dealz.api.model.converter;

import com.onion.dealz.api.model.dto.ShopAddDto;
import com.onion.dealz.api.model.dto.ShopDto;
import com.onion.dealz.api.model.dto.UserDto;
import com.onion.dealz.api.model.entity.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopDtoConverter {
    public ShopDto entityToDto(Shop shop) {
        ShopDto shopDto = new ShopDto();
        shopDto.setId(shop.getId());
        shopDto.setName(shop.getName());
        shopDto.setLink(shop.getLink());
        shopDto.setCountry(shop.getCountry());
        shopDto.setDescription(shop.getDescription());
        return shopDto;
    }

    public Shop dtoAddToEntity(ShopDto shopDto){
        Shop shop = new Shop();
        shop.setName(shopDto.getName());
        shop.setLink(shopDto.getLink());
        shop.setDescription(shopDto.getDescription());
        shop.setCountry(shopDto.getCountry());
        return shop;
    }

    public List<ShopDto> entityToDtoList(List<Shop> shops){
        List<ShopDto> shopDtos = new ArrayList();
        for(Shop s: shops){
            shopDtos.add(entityToDto(s));
        }
        return shopDtos;
    }
}
