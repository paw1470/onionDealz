package com.onion.dealz.api.service;

import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.PromotionDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService{

    private PromotionDao promotionDao;

    @Cacheable
    @Override
    public List<Promotion> getAllPromotions() {
        return this.promotionDao.findAllPromotions();
    }

    @Cacheable
    @Override
    public Promotion getByPromotionId(Long id) {
        return this.promotionDao.findById(id);
    }

    @Override
    public List<Promotion> getAllByUserId(Long id) {
        return this.promotionDao.findAllByUserId(id);
    }

    @Override
    public List<Promotion> getAllByShopId(Long id) {
        return this.promotionDao.findAllByShopId(id);
    }

    @Override
    public List<Promotion> getAllByTagId(Long id) {
        return this.promotionDao.findAllByTagId(id);
    }

    @Override
    public void create(Promotion promotion) {
        promotionDao.addPromotion(promotion);
    }

    @Override
    public void delete(Promotion promotion) {
        promotionDao.deletePromotion(promotion);
    }

    @Override
    public void update(Promotion promotion) {
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public void addLike(Promotion promotion, User user) {
        promotionDao.removeUnlike(promotion, user);
        promotionDao.addLike(promotion, user);
    }

    @Override
    public void addUnlike(Promotion promotion, User user) {
        promotionDao.removeLike(promotion, user);
        promotionDao.addUnlike(promotion, user);
    }

    @Override
    public void addComment(Promotion promotion, Comment comment) {
        promotionDao.addComment(promotion, comment);
    }

    @Override
    public void removeLike(Promotion promotion, User user) {
        promotionDao.removeLike(promotion, user);
    }

    @Override
    public void removeUnlike(Promotion promotion, User user) {
        promotionDao.removeUnlike(promotion, user);
    }

    @Override
    public void removeComment(Promotion promotion, Comment comment) {
        promotionDao.removeComment(promotion, comment);
    }
}
