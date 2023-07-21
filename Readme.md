### Open API / Swagger documentation
the following is the link to the api documentation, generated with open API

http://localhost:8080/inditex/swagger-ui/index.html


### Successful Request

```console
curl --request GET \
  --url http://localhost:8080/inditex/api/get-prices \
  --header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
  --header 'Content-Type: multipart/form-data' \
  --cookie JSESSIONID=A34202711F0DC5D4F01A23C33BA15267 \
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
  --header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
  --header 'Content-Type: multipart/form-data' \
  --cookie JSESSIONID=A34202711F0DC5D4F01A23C33BA15267 \
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






Se ha agregado un plus a la implementacion la cual es agregarle  **_Spring Security_**  con  **_JWT_**

Para ello se ha agregado el controller llamado **_AuthController_** el cual contiene el Endpoint encargado de hacer login en la aplicacion y retonar 
un TOKEN JWT valido y su repuesta sera 200 si es valido

```console

curl --request POST \
  --url http://localhost:8080/inditex/api/auth/login \
  --header 'Authorization: Basic YWRtaW46YWRtaW4xMjM=' \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=65877303BFDE5053ABEBEDBB9B0DA6E5 \
  --data '{
	"username": "admin",
	"password": "admin123"
}'

```


