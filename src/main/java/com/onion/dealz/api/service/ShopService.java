package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.ShopDto;
import com.onion.dealz.api.model.entity.Shop;

import java.util.List;

public interface ShopService {
    public List<ShopDto> getAllShops();
    public ShopDto getByShopId(Long id);
    public Shop getByShopIdEntity(Long id);
    public List<ShopDto> getAllByName(String name);

    public ShopDto create(ShopDto shop);
    public void delete(Long id);
    public ShopDto update(Long id, ShopDto shop);

    public ShopDto getByShopName(String name);
}
