drop table if exists PRICES;

create table PRICES(
  ID int not null AUTO_INCREMENT,
  BRAND_ID int,
  START_DATE TIMESTAMP,
  END_DATE TIMESTAMP,
  PRICE_LIST int,
  PRODUCT_ID int,
  PRIORITY int,
  PRICE numeric(20, 2),
  CURR varchar(5),
  PRIMARY KEY (ID)
);