package com.payment.system.mapper;

import com.payment.system.dto.response.StockResponse;
import com.payment.system.entity.StockEntity;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    List<StockResponse> toStockResponses(List<StockEntity> stockEntities);
}
