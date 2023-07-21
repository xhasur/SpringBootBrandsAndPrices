### Open API / Swagger documentation
the following is the link to the api documentation, generated with open API

http://localhost:8080/inditex/swagger-ui/index.html


### Successful Request

```console
curl --request GET \
curl --request GET \
  --url http://localhost:8080/inditex/api/get-prices \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImFwaSIsImlhdCI6MTY4OTk1OTk1MiwiZXhwIjoxNjg5OTY3MTUyfQ.ysMu0j305wQWCFrs8yPoTOSBI0Sic1zhC-KkPlB3B9k' \
  --header 'Content-Type: multipart/form-data' \
  --cookie JSESSIONID=E840FF384BD57F00C5DAEA54BBEA608C \
  --form applicationDate=2020-10-12T07:30:10Z \
  --form productId=35455 \
  --form brandId=1
```

### Response

```json
{"brandId":"1",
"startDate":"2020-06-15T16:00:00",
"endDate":"2020-12-31T23:59:59",
"priceList":"4",
"productId":"35455",
"price":38.95
}
```



### Request - BAD REQUEST

```console
curl --request GET \
  --url http://localhost:8080/inditex/api/get-prices \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImFwaSIsImlhdCI6MTY4OTk1OTk1MiwiZXhwIjoxNjg5OTY3MTUyfQ.ysMu0j305wQWCFrs8yPoTOSBI0Sic1zhC-KkPlB3B9k' \
  --header 'Content-Type: multipart/form-data' \
  --cookie JSESSIONID=1FA01D65F9193C4AE44B979E3206F392 \
  --form applicationDate=2029-10-12T07:30:10Z \
  --form productId=35455 \
  --form brandId=1
```

### Response

```json
{"timeStamp":"2023-07-02T10:07:22.281+00:00",
 "message":"Error getting prices with Date: 2028-10-12T07:30:10productId35455BrandId1",
 "details":"uri=/inditex/api/get-prices",
 "httpStatus":"BAD_REQUEST"
}
```


### TABLE PRICES


| BRAND_ID | START_DATE   | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|---|---------------------|----------------------|------------|-------|----------|-------|----|
| 1 | 2020-06-14-00.00.00 | 2020-12-31-23.59.59  | 1          | 35455 | 0        | 35.50 | EUR |
| 1 | 2020-06-14-15.00.00 | 2020-06-14-18.30.00  | 2          | 35455 | 1        | 25.45 | EUR |
| 1 | 2020-06-15-00.00.00 | 2020-06-15-11.00.00  | 3          | 35455 | 1        | 30.50 | EUR |
| 1 | 2020-06-15-16.00.00 | 2020-12-31-23.59.59  | 4          | 35455 | 1        | 38.95 | EUR |

### Campos:

- BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
- START_DATE , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
- PRICE_LIST: Identificador de la tarifa de precios aplicable.
- PRODUCT_ID: Identificador código de producto.
- PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
- PRICE: precio final de venta.
- CURR: iso de la moneda.






A plus has been added to this implementation which is to add **_Spring Security_** with **_JWT_**

For this, the controller called **_AuthController_** has been added, which contains the Endpoint in charge of logging into the application and restarting
a valid JWT TOKEN and its response will be 200 if it is valid

```console

curl --request POST \
  --url http://localhost:8080/inditex/api/auth/login \
  --header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=1FA01D65F9193C4AE44B979E3206F392 \
  --data '{
	"username": "admin",
	"password": "admin123"
}'

```

 