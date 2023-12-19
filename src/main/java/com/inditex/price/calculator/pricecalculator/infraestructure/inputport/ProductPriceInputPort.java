package com.inditex.price.calculator.pricecalculator.infraestructure.inputport;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductPriceInputPort {

    public List<ProductPrice> getAll();

    public Optional<ProductPrice> getPrice(LocalDateTime calculationDate, Long productId, Long brandId);

}
