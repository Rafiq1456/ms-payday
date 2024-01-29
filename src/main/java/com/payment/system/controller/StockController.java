package com.payment.system.controller;

import static com.payment.system.constant.AuthRole.ROLE_USER;

import com.payment.system.dto.response.StockResponse;
import com.payment.system.service.StockService;
import java.util.List;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stock")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StockController {
    StockService stockService;

    @GetMapping
    @PreAuthorize(ROLE_USER)
    @ResponseStatus(HttpStatus.OK)
    List<StockResponse> getStocks(){
        return stockService.getStocks();
    }


    @PostMapping
    @PreAuthorize(ROLE_USER)
    @ResponseStatus(HttpStatus.OK)
    void saveStocks(){
        stockService.saveStocks();
    }
}
