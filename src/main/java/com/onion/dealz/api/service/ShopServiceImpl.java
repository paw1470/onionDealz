package com.onion.dealz.api.service;

import com.onion.dealz.api.exception.ResourceNotFoundException;
import com.onion.dealz.api.model.converter.ShopDtoConverter;
import com.onion.dealz.api.model.dto.ShopDto;
import com.onion.dealz.api.model.entity.Shop;
import com.onion.dealz.api.repository.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    ShopDtoConverter shopDtoConverter = new ShopDtoConverter();

    @Override
    public List<ShopDto> getAllShops() {
        List<Shop> shops = shopDao.findAllShops();
        List<ShopDto> shopDtos = shopDtoConverter.entityToDtoList(shops);
        return shopDtos;
    }

    @Override
    public Shop getByShopIdEntity(Long id) {
        Shop shop = shopDao.findById(id);
        if(shop == null)
            throw new ResourceNotFoundException();
        return shopDao.findById(id);
    }

    @Override
    public ShopDto getByShopId(Long id) {
        Shop shop = getByShopIdEntity(id);
        ShopDto shopDto = shopDtoConverter.entityToDto(shop);
        return shopDto;
    }

    @Override
    public List<ShopDto> getAllByName(String name) {
        List<ShopDto> shopDtos = shopDtoConverter.entityToDtoList(shopDao.findAllByName(name));
        return shopDtos;
    }

    @Override
    public ShopDto create(ShopDto shopDto) {
        Shop shop = shopDtoConverter.dtoAddToEntity(shopDto);
        shopDao.create(shop);
        ShopDto shopDtoNew = shopDtoConverter.entityToDto(shopDao.findById(shop.getId()));
        return shopDtoNew;
    }

    @Override
    public void delete(Long id) {
        Shop shop = getByShopIdEntity(id);
        shopDao.deleteShop(shop);
    }

    @Override
    public ShopDto update(Long id, ShopDto shopDto) {
        Shop shop = getByShopIdEntity(id);
        shop.update(shopDto);
        shopDao.updateShop(shop);
        ShopDto shopDtoNew = shopDtoConverter.entityToDto(shopDao.findById(id));
        return shopDtoNew;
    }

    @Override
    public ShopDto getByShopName(String name) {
        Shop shop = shopDao.findByName(name);
        if(shop == null)
            throw new ResourceNotFoundException();
        ShopDto shopDto = shopDtoConverter.entityToDto(shop);
        return shopDto;
    }
}
