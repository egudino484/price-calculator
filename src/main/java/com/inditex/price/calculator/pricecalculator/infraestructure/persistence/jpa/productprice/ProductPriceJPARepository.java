package com.inditex.price.calculator.pricecalculator.infraestructure.persistence.jpa.productprice;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import com.inditex.price.calculator.pricecalculator.domain.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductPriceJPARepository implements ProductPriceRepository {

    @Autowired
    ProductPriceJPARepositoryInterface crudRepository;

    @Override
    public List<ProductPrice> getAll() {

        return (List<ProductPrice>) crudRepository.findAll();
    }

    @Override
    public List<ProductPrice> findPrices(LocalDateTime calculationDate, Long productId, Long brandId) {
        return crudRepository.getPricesForProductInSpecificDate(productId, brandId, calculationDate);
    }

}
