package com.inditex.price.calculator.pricecalculator.infraestructure.inputadapter.http;


import com.inditex.price.calculator.pricecalculator.common.DateUtils;
import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import com.inditex.price.calculator.pricecalculator.infraestructure.inputadapter.http.response.PriceResponse;
import com.inditex.price.calculator.pricecalculator.infraestructure.inputport.ProductPriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "product-price")
public class ProductPriceAPI {

    @Autowired
    ProductPriceInputPort productPriceInputPort;

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductPrice> getAll() {
        return productPriceInputPort.getAll();
    }

    @GetMapping("/get-price")
    public ResponseEntity<PriceResponse> getPriceProduct(
            @RequestParam("date") String dateString,
            @RequestParam("product_id") Long productId,
            @RequestParam("brand_id") Long brandId) {

        if (!DateUtils.isValidDateFormat(dateString)) {
            return ResponseEntity.badRequest().build();
        }
        LocalDateTime calculationDateTime = DateUtils.parseToLocalDateTime(dateString);

        Optional<ProductPrice> price = productPriceInputPort.getPrice(calculationDateTime, productId, brandId);
        if (!price.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PriceResponse(price.get()));
    }


}
