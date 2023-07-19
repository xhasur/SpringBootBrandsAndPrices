drop table if exists PRICES;
drop table if exists USERS;

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


create table USERS(
  USERNAME varchar(10) not null,
  PASSWORD varchar(200) not null,
  PRIMARY KEY (USERNAME)
);