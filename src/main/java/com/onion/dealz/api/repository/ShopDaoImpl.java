package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Shop;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> findAllShops() {
        return entityManager.createNamedQuery("@GET_ALL_SHOPS").getResultList();
    }

    @Override
    public Shop findById(Long id) {
        return entityManager.find(Shop.class, id);
    }

    @Override
    public List<Shop> findAllByName(String name) {
        return entityManager.createNamedQuery("@GET_ALL_SHOPS_BY_NAME").
                setParameter("name", name).
                getResultList();
    }

    @Override
    public void create(Shop shop) {
        entityManager.persist(shop);
    }

    @Override
    public void deleteShop(Shop shop) {
        entityManager.remove(shop);
    }

    @Override
    public void updateShop(Shop shop) {
        entityManager.flush();
    }

    @Override
    public Shop findByName(String name) {
        List<Shop> users = entityManager.createNamedQuery("@GET_SHOP_BY_NAME").
                setParameter("name", name).
                getResultList();
        for(Shop u: users){
            if(u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }
}
