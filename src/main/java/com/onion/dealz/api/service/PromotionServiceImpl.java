package com.onion.dealz.api.service;

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
        PromotionDto promotionDto = promotionDtoConverter.entityToDto(promotionDao.findById(id));
        return promotionDto;
    }

    @Override
    public List<PromotionDto> getAllByUserId(Long id) {
        List<PromotionDto> promotionDto = promotionDtoConverter.entityToDtoList(promotionDao.findAllByUserId(id));
        return promotionDto;


    }

    @Override
    public List<PromotionDto> getAllByShopId(Long id) {
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotionDao.findAllByShopId(id));
        return promotionDtos;
    }

    @Override
    public List<PromotionDto> getAllByTagId(Long id) {
        List<PromotionDto> promotionDtos = promotionDtoConverter.entityToDtoList(promotionDao.findAllByTagId(id));
        return promotionDtos;
    }

    @Override
    public PromotionDto create(PromotionDto promotionDto) {
        User user = userService.getByUserIdEntity(Long.valueOf(1));   //todo poprawic jak bedzie autoryzacja
        Shop shop = shopService.getByShopIdEntity(promotionDto.getShop().getId());
        Promotion promotion = promotionDtoConverter.dtoAddToEntity(promotionDto, user, shop);
        promotionDao.addPromotion(promotion);
        PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionDao.findById(promotion.getId()));
        return promotionDtoNew;
    }

    @Override
    public void delete(Long id) {
        Promotion promotion = promotionDao.findById(id);
        promotionDao.deletePromotion(promotion);
    }

    @Override
    public PromotionDto update(Long id, PromotionDto promotionDto) {
        Promotion promotion = promotionDao.findById(id);
        promotion.update(promotionDto);
        promotionDao.updatePromotion(promotion);
        PromotionDto promotionDtoNew = promotionDtoConverter.entityToDto(promotionDao.findById(id));
        return promotionDtoNew;
    }

    @Override
    public void addLike(Long id) {
        User user = userService.getByUserIdEntity(Long.valueOf(1));   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = promotionDao.findById(id);
        promotion.addLike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public void addUnlike(Long id) {
        User user = userService.getByUserIdEntity(Long.valueOf(1));   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = promotionDao.findById(id);
        promotion.addUnlike(user);
        promotionDao.updatePromotion(promotion);
    }


    @Override
    public void removeLike(Long id) {
        User user = userService.getByUserIdEntity(Long.valueOf(1));   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = promotionDao.findById(id);
        promotion.removeLike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public void removeUnlike(Long id) {
        User user = userService.getByUserIdEntity(Long.valueOf(1));   //todo poprawic jak bedzie autoryzacja
        Promotion promotion = promotionDao.findById(id);
        promotion.removeUnlike(user);
        promotionDao.updatePromotion(promotion);
    }

    @Override
    public Promotion getPromotionByIdEntity(Long id) {
        Promotion promotion = promotionDao.findById(id);
        return promotion;
    }
}
