# DATABASE

## PRICES

| BRAND_ID | START_DATE           | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|----------------------|----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00  | 2020-12-31-23.59.59  | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00  | 2020-06-14-18.30.00  | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00  | 2020-06-15-11.00.00  | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00  | 2020-12-31-23.59.59  | 4          | 35455      | 1        | 38.95 | EUR  |

## Endpoints:

### Get Price for a Product


Endpoints:

```bash
curl -X GET 'http://localhost:8080/product-price/get-price?product_id=35455&brand_id=1&date=2020-06-14-10.00.00'
```
date: Date for which the product price is requested in the format ("yyyy-MM-dd-HH.mm.ss") Example: 2023-12-31-23.59.59
product_id: id of the product
brand_id: id of the brand

In case you need all the information:

```bash
curl -X GET 'http://localhost:8080/product-price/get-all'
```