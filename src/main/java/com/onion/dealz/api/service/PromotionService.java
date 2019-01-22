package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Comment;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.User;

import java.util.List;
import java.util.Set;

public interface PromotionService {

    public List<Promotion> getAllPromotions();
    public Promotion getByPromotionId(Long id);
    public List<Promotion> getAllByUserId(Long id);
    public List<Promotion> getAllByShopId(Long id);
    public List<Promotion> getAllByTagId(Long id);

    public void create(Promotion promotion);
    public void delete(Promotion promotion);
    public void update(Promotion promotion);

    public void addLike(Promotion promotion, User user);
    public void addUnlike(Promotion promotion, User user);
    public void addComment(Promotion promotion, Comment comment);

    public void removeLike(Promotion promotion, User user);
    public void removeUnlike(Promotion promotion, User user);
    public void removeComment(Promotion promotion, Comment comment);
}
