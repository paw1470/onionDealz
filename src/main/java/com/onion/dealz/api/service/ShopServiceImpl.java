package com.onion.dealz.api.service;

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

    @Override
    public List<Shop> getAllShops() {
        return this.shopDao.findAllShops();
    }

    @Override
    public Shop getByShopId(Long id) {
        return this.shopDao.findById(id);
    }

    @Override
    public List<Shop> getAllByName(String name) {
        return this.shopDao.findAllByName(name);
    }

    @Override
    public void create(Shop shop) {
        this.shopDao.create(shop);
    }

    @Override
    public void delete(Shop shop) {
        this.shopDao.deleteShop(shop);
    }

    @Override
    public void update(Shop shop) {
        this.shopDao.updateShop(shop);
    }

    @Override
    public Shop getByShopName(String name) {
        return this.shopDao.findByName(name);
    }
}
