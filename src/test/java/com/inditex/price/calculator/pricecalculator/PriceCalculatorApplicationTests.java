package com.inditex.price.calculator.pricecalculator;

import com.inditex.price.calculator.pricecalculator.common.DateUtils;
import com.inditex.price.calculator.pricecalculator.infraestructure.inputadapter.http.response.PriceResponse;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceCalculatorApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;

    @Before
    public void setUp() throws Exception {
        // for now  datainitializer will be executed but in case we need another test data we can add it here
		/*
		| id | brandId | startDate           | endDate             | priceList | productId | priority | price | currency |
|----|---------|---------------------|---------------------|-----------|-----------|----------|-------|----------|
| 1  | 1       | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 | 1         | 35455     | 0        | 35.5  | EUR      |
| 2  | 1       | 2020-06-14T15:00:00 | 2020-06-14T18:30:00 | 2         | 35455     | 1        | 25.45 | EUR      |
| 3  | 1       | 2020-06-15T00:00:00 | 2020-06-15T11:00:00 | 3         | 35455     | 1        | 30.5  | EUR      |
| 4  | 1       | 2020-06-15T16:00:00 | 2020-12-31T23:59:59 | 4         | 35455     | 1        | 38.95 | EUR      |
		* */
    }

    @Test
    public void test1() throws URISyntaxException {
        //| 1  | 1       | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 | 1         | 35455     | 0        | 35.5  | EUR      |
        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-14-10.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);

        PriceResponse expectedResponse = new PriceResponse(35455L, 1, DateUtils.parseToLocalDateTime("2020-06-14-00.00.00"), DateUtils.parseToLocalDateTime("2020-12-31-23.59.59"), 1, 35.5, "EUR");


        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(expectedResponse.getPrice(), result.getBody().getPrice());
        Assertions.assertEquals(expectedResponse.getPriceList(), result.getBody().getPriceList());
        Assertions.assertEquals(expectedResponse.getStartDate(), result.getBody().getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), result.getBody().getEndDate());
        Assertions.assertEquals(expectedResponse.getBrandId(), result.getBody().getBrandId());
        Assertions.assertEquals(expectedResponse.getProductId(), result.getBody().getProductId());
    }

    @Test
    public void test2() throws URISyntaxException {
        //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        // | 2  | 1       | 2020-06-14T15:00:00 | 2020-06-14T18:30:00 | 2         | 35455     | 1        | 25.45 | EUR      |
        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-14-16.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);

        PriceResponse expectedResponse = new PriceResponse(35455L, 1, DateUtils.parseToLocalDateTime("2020-06-14-15.00.00"), DateUtils.parseToLocalDateTime("2020-06-14-18.30.00"), 2, 25.45, "EUR");

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(expectedResponse.getPrice(), result.getBody().getPrice());
        Assertions.assertEquals(expectedResponse.getPriceList(), result.getBody().getPriceList());
        Assertions.assertEquals(expectedResponse.getStartDate(), result.getBody().getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), result.getBody().getEndDate());
        Assertions.assertEquals(expectedResponse.getBrandId(), result.getBody().getBrandId());
        Assertions.assertEquals(expectedResponse.getProductId(), result.getBody().getProductId());

    }

    @Test
    public void test3() throws URISyntaxException {
        //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
        //expected | 1  | 1       | 2020-06-14T00:00:00 | 2020-12-31T23:59:59 | 1         | 35455     | 0        | 35.5  | EUR      |
        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-14-21.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);

        PriceResponse expectedResponse = new PriceResponse(35455L, 1, DateUtils.parseToLocalDateTime("2020-06-14-00.00.00"), DateUtils.parseToLocalDateTime("2020-12-31-23.59.59"), 1, 35.5, "EUR");


        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(expectedResponse.getPrice(), result.getBody().getPrice());
        Assertions.assertEquals(expectedResponse.getPriceList(), result.getBody().getPriceList());
        Assertions.assertEquals(expectedResponse.getStartDate(), result.getBody().getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), result.getBody().getEndDate());
        Assertions.assertEquals(expectedResponse.getBrandId(), result.getBody().getBrandId());
        Assertions.assertEquals(expectedResponse.getProductId(), result.getBody().getProductId());

    }

    @Test
    public void test4() throws URISyntaxException {
        //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
        //expected | 3  | 1       | 2020-06-15T00:00:00 | 2020-06-15T11:00:00 | 3         | 35455     | 1        | 30.5  | EUR      |
        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-15-10.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);

        PriceResponse expectedResponse = new PriceResponse(35455L, 1, DateUtils.parseToLocalDateTime("2020-06-15-00.00.00"), DateUtils.parseToLocalDateTime("2020-06-15-11.00.00"), 3, 30.5, "EUR");


        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(expectedResponse.getPrice(), result.getBody().getPrice());
        Assertions.assertEquals(expectedResponse.getPriceList(), result.getBody().getPriceList());
        Assertions.assertEquals(expectedResponse.getStartDate(), result.getBody().getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), result.getBody().getEndDate());
        Assertions.assertEquals(expectedResponse.getBrandId(), result.getBody().getBrandId());
        Assertions.assertEquals(expectedResponse.getProductId(), result.getBody().getProductId());


    }

    @Test
    public void test5() throws URISyntaxException {
        //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
        //expected | 4  | 1       | 2020-06-15T16:00:00 | 2020-12-31T23:59:59 | 4         | 35455     | 1        | 38.95 | EUR      |
        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-16-21.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);

        PriceResponse expectedResponse = new PriceResponse(35455L, 1, DateUtils.parseToLocalDateTime("2020-06-15-16.00.00"), DateUtils.parseToLocalDateTime("2020-12-31-23.59.59"), 4, 38.95, "EUR");

        Assertions.assertEquals(200, result.getStatusCode().value());
        Assertions.assertEquals(expectedResponse.getPrice(), result.getBody().getPrice());
        Assertions.assertEquals(expectedResponse.getPriceList(), result.getBody().getPriceList());
        Assertions.assertEquals(expectedResponse.getStartDate(), result.getBody().getStartDate());
        Assertions.assertEquals(expectedResponse.getEndDate(), result.getBody().getEndDate());
        Assertions.assertEquals(expectedResponse.getBrandId(), result.getBody().getBrandId());
        Assertions.assertEquals(expectedResponse.getProductId(), result.getBody().getProductId());

    }

    @Test
    public void testProductNotFound() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + randomServerPort + "/product-price/get-price?product_id=132&brand_id=1&date=2020-06-16-21.00.00";
        URI uri = new URI(baseUrl);

        ResponseEntity<PriceResponse> result = this.restTemplate.getForEntity(uri, PriceResponse.class);


        Assertions.assertEquals(404, result.getStatusCode().value());


    }


}
