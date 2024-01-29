package com.payment.system.repository;

import com.payment.system.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long>  {
}
