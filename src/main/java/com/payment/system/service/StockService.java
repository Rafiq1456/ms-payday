package com.payment.system.service;

import com.payment.system.dto.response.StockResponse;
import com.payment.system.entity.StockEntity;
import com.payment.system.mapper.StockMapper;
import com.payment.system.repository.StockRepository;
import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StockService {
    StockRepository stockRepository;

    StockMapper stockMapper;
    public List<StockResponse> getStocks() {
        List<StockEntity> stockEntities = stockRepository.findAll();
        return stockMapper.toStockResponses(stockEntities);
    }

    public void saveStocks() {
        StockEntity stockEntity = StockEntity.builder()
                .companyName("Kontakt Home").content("Kontakt Home").price(100L).symbol("KH").build();
        StockEntity stockEntity2 = StockEntity.builder()
                .companyName("Irshad").content("I").price(200L).symbol("KH").build();
        stockRepository.saveAll(List.of(stockEntity,stockEntity2));
    }
}
