package com.onion.dealz.api.service;

import com.onion.dealz.api.model.entity.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> getAllShops();
    public Shop getByShopId(Long id);
    public List<Shop> getAllByName(String name);

    public void create(Shop shop);
    public void delete(Shop shop);
    public void update(Shop shop);

    public Shop getByShopName(String name);
}
