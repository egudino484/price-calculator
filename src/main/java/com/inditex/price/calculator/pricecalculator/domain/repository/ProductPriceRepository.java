package com.inditex.price.calculator.pricecalculator.domain.repository;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductPriceRepository {
    public List<ProductPrice> getAll();

    public List<ProductPrice> findPrices(LocalDateTime calculationDate, Long productId, Long brandId);
}
