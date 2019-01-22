package com.onion.dealz.api.repository;


import com.onion.dealz.api.model.entity.Promotion;

import java.util.List;



public interface PromotionDao{

    Promotion findById(Long id);
    List<Promotion> findAllPromotions();
    List<Promotion> findAllByUserId(Long id);
    List<Promotion> findAllByShopId(Long id);
    List<Promotion> findAllByTagId(Long id);

    void addPromotion(Promotion promotion);
    void deletePromotion(Promotion promotion);
    void updatePromotion(Promotion promotion);

}
