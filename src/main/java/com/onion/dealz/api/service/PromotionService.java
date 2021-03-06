package com.onion.dealz.api.service;

import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Promotion;

import java.util.List;

public interface PromotionService {

    public List<PromotionDto> getAllPromotions();
    public PromotionDto getPromotionById(Long id);
    public List<PromotionDto> getAllByUserId(Long id);
    public List<PromotionDto> getAllByShopId(Long id);
    public List<PromotionDto> getAllByTagId(Long id);

    public PromotionDto create(PromotionDto promotionDto);
    public void delete(Long id);
    public PromotionDto update(Long id, PromotionDto promotionDto);

    public void addLike(Long promotionId, Long userId);
    public void addUnlike(Long promotionId, Long userId);

    public void removeLike(Long promotionId, Long userId);
    public void removeUnlike(Long promotionId, Long userId);

    public Promotion getPromotionByIdEntity(Long id);
}
