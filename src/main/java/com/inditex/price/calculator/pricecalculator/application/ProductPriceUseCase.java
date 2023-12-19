package com.inditex.price.calculator.pricecalculator.application;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import com.inditex.price.calculator.pricecalculator.domain.repository.ProductPriceRepository;
import com.inditex.price.calculator.pricecalculator.infraestructure.inputport.ProductPriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ProductPriceUseCase implements ProductPriceInputPort {

    @Autowired
    ProductPriceRepository productRepository;


    @Override
    public List<ProductPrice> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Optional<ProductPrice> getPrice(LocalDateTime calculationDate, Long productId, Long brandId) {
        Optional<ProductPrice> priceOptional = productRepository.findPrices(calculationDate, productId, brandId).stream().findFirst();
        return priceOptional;
    }

}
