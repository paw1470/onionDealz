package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Promotion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PromotionDaoImpl implements PromotionDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Promotion findById(Long id) {
        return entityManager.find(Promotion.class, id);
    }

    @Override
    public List<Promotion> findAllPromotions() {
        return entityManager.createNamedQuery("@GET_ALL_PROMOTIONS").getResultList();
    }

    @Override
    public List<Promotion> findAllByUserId(Long id) {
        return entityManager.createNamedQuery("@GET_PROMOTIONS_BY_USER_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Promotion> findAllByShopId(Long id) {
        return entityManager.createNamedQuery("@GET_PROMOTIONS_BY_SHOP_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Promotion> findAllByTagId(Long id) {
        return entityManager.createNamedQuery("@GET_PROMOTIONS_BY_TAG_ID")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public void addPromotion(Promotion promotion) {
        entityManager.persist(promotion);
    }

    @Override
    public void deletePromotion(Promotion promotion) {
        entityManager.remove(findById(promotion.getId()));
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        entityManager.flush();
    }
}
