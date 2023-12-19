package com.inditex.price.calculator.pricecalculator.infraestructure.persistence.jpa.productprice;

import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductPriceJPARepositoryInterface extends CrudRepository<ProductPrice, Long> {
    @Query("SELECT p FROM ProductPrice p where p.productId = :productId and p.brandId = :brandId and :calculationDate BETWEEN p.startDate AND p.endDate order by priority DESC")
    List<ProductPrice> getPricesForProductInSpecificDate(@Param("productId") Long productId, @Param("brandId") Long brandId, @Param("calculationDate") LocalDateTime calculationDate);

    List<ProductPrice> findByProductId(long productId);

}
