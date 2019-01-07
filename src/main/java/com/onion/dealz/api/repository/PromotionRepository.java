package com.onion.dealz.api.repository;

import com.onion.dealz.api.model.entity.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
}
