package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Shop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ShopDao{
    List<Shop> findAllShops();

    Shop findById(Long id);

    List<Shop> findAllByName(String name);

    void create(Shop shop);

    void deleteShop(Shop shop);

    void updateShop(Shop shop);

    Shop findByName(String name);
}
