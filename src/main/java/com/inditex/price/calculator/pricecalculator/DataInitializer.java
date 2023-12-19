package com.inditex.price.calculator.pricecalculator;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import com.inditex.price.calculator.pricecalculator.infraestructure.persistence.jpa.productprice.ProductPriceJPARepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductPriceJPARepositoryInterface priceRepository;

    @Autowired
    public DataInitializer(ProductPriceJPARepositoryInterface priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public void run(String... args) {
        List<ProductPrice> prices = List.of(
                new ProductPrice(1, 1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, 35455L, 0, 35.50, "EUR"),
                new ProductPrice(2, 1, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 2, 35455L, 1, 25.45, "EUR"),
                new ProductPrice(3, 1, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 3, 35455L, 1, 30.50, "EUR"),
                new ProductPrice(4, 1, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 4, 35455L, 1, 38.95, "EUR")
        );

        priceRepository.saveAll(prices);
    }
}