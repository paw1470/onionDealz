package com.onion.dealz.api.service;

import com.onion.dealz.api.exception.ResourceNotFoundException;
import com.onion.dealz.api.model.converter.PromotionDtoConverter;
import com.onion.dealz.api.model.dto.PromotionDto;
import com.onion.dealz.api.model.entity.Promotion;
import com.onion.dealz.api.model.entity.Shop;
import com.onion.dealz.api.model.entity.User;
import com.onion.dealz.api.repository.PromotionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PromotionServiceImpl implements PromotionService{

    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private ShopService shopService;

    @Autowired
    private UserService userService;

    private PromotionDtoConverter promotionDtoConverter = new PromotionDtoConverter();

    @Cacheable
    @Override
    public List<PromotionDto> getAllPromotions() {
        List<Promotion> promotions = promotionDao.findAllPromotions();
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotions);
        return promotionDtos;
    }

    @Cacheable
    @Override
    public PromotionDto getPromotionById(Long id) {
        Promotion promotion = getPromotionByIdEntity(id);
        PromotionDto promotionDto = promotionDtoConverter.entityToDto(promotion);
        return promotionDto;
    }

    @Override
    public List<PromotionDto> getAllByUserId(Long id) {
        List<Promotion> promotions = promotionDao.findAllByUserId(id);
        List<PromotionDto> promotionDto = promotionDtoConverter.entityToDtoList(promotions);
        return promotionDto;


    }

    @Override
    public List<PromotionDto> getAllByShopId(Long id) {
        List<Promotion> promotions = promotionDao.findAllByShopId(id);
                List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotions);
        return promotionDtos;
    }

    @Override
    public List<PromotionDto> getAllByTagId(Long id) {
        List<Promotion> promotions = promotionDao.findAllByTagId(id);
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotions);
        return promotionDtos;
    }

    @Override
    public PromotionDto create(PromotionDto promotionDto) {
        User user = userService.getByUserIdEntity(promotionDto.getUser().getId());   //todo poprawic jak bedzie autoryzacja
        Shop shop = shopService.getByShopIdEntity(promotionDto.getShop().getId());
        Promotion promotion = promotionDtoConverter.dtoAddToEntity(promotionDto, user, shop);
        promotionDao.addPromotion(promotion);
        PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionDao.findById(promotion.getId()));
        return promotionDtoNew;
    }

    @Override
    public void delete(Long id) {
        Promotion promotion = getPromotionByIdEntity(id);
        promotionDao.deletePromotion(promotion);
    }

    @Override
    public PromotionDto update(Long id, PromotionDto promotionDto) {
        Promotion promotion = getPromotionByIdEntity(id);
        promotion.update(promotionDto);
        promotionDao.updatePromotion(promotion);
        PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionDao.findById(id));
        return promotionDtoNew;
    }

    @Override
    public void addLike(Long promotionId, Long userId) {
        User user = userService.getByUserIdEntity(userId);   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = getPromotionByIdEntity(promotionId);
        promotion.addLike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public void addUnlike(Long promotionId, Long userId) {
        User user = userService.getByUserIdEntity(userId);   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = getPromotionByIdEntity(promotionId);
        promotion.addUnlike(user);
        promotionDao.updatePromotion(promotion);
    }


    @Override
    public void removeLike(Long promotionId, Long userId) {
        User user = userService.getByUserIdEntity(userId);   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = getPromotionByIdEntity(promotionId);
        promotion.removeLike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public void removeUnlike(Long promotionId, Long userId) {
        User user = userService.getByUserIdEntity(userId);   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = getPromotionByIdEntity(promotionId);
        promotion.removeUnlike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public Promotion getPromotionByIdEntity(Long id) {
        Promotion promotion = promotionDao.findById(id);
        if(promotion == null)
            throw new ResourceNotFoundException();
        return promotion;
    }
}
