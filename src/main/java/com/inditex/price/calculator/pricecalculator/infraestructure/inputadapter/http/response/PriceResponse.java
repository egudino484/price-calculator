package com.inditex.price.calculator.pricecalculator.infraestructure.inputadapter.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inditex.price.calculator.pricecalculator.domain.ProductPrice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PriceResponse {

    @JsonProperty("product_id")
    private Long productId;
    @JsonProperty("brand_id")
    private long brandId;
    @JsonProperty("start_date")
    private LocalDateTime startDate;
    @JsonProperty("end_date")
    private LocalDateTime endDate;
    @JsonProperty("price_list")
    private Integer priceList;

    @JsonProperty("price")
    private Double price = 0.0;
    @JsonProperty("currency")
    private String currency;

    public PriceResponse() {
    }

    public PriceResponse(Long productId, long brandId, LocalDateTime startDate, LocalDateTime endDate, Integer priceList, Double price, String currency) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceList = priceList;
        this.price = price;
        this.currency = currency;
    }

    public PriceResponse(ProductPrice price) {
        this.productId = price.getProductId();
        this.brandId = price.getBrandId();
        this.startDate = price.getStartDate();
        this.endDate = price.getEndDate();
        this.priceList = price.getPriceList();
        this.price = price.getPrice();
        this.currency = price.getCurrency();
    }
}
