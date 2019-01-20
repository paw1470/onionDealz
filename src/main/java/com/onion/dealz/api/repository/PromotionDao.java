package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;


public interface PromotionDao{

    Promotion findById(Long id);
    List<Promotion> findAllPromotions();
    List<Promotion> findAllByUserId(Long id);
    List<Promotion> findAllByShopId(Long id);
    List<Promotion> findAllByTagId(Long id);

    void addPromotion(Promotion promotion);
    void deletePromotion(Promotion promotion);
    void updatePromotion(Promotion promotion);

    void addLike(Promotion promotion, User user);
    void addUnlike(Promotion promotion, User user);
    void addComment(Promotion promotion, Comment comment);

    void removeLike(Promotion promotion, User user);
    void removeUnlike(Promotion promotion, User user);
    void removeComment(Promotion promotion, Comment comment);
}
