CREATE DATABASE SHOP_STYLE;
\c shop_style

CREATE SCHEMA IF NOT EXISTS MS_CUSTOMER_SCH;
CREATE USER MS_CUSTOMER WITH  PASSWORD 'teste';
GRANT USAGE ON SCHEMA MS_CUSTOMER_SCH TO MS_CUSTOMER;
GRANT CONNECT ON DATABASE SHOP_STYLE TO ms_customer;

CREATE TABLE IF NOT EXISTS MS_CUSTOMER_SCH.CUSTOMER(
    ID INT GENERATED ALWAYS AS IDENTITY,
    CPF CHAR(11) NOT NULL,
    FIRST_NAME TEXT NOT NULL,
    LAST_NAME TEXT NOT NULL,
    SEX CHAR(1) NOT NULL,
    BIRTHDATE DATE NOT NULL,
    EMAIL TEXT NOT NULL,
    PASSWORD TEXT NOT NULL,
    ACTIVE BOOLEAN NOT NULL,
    CONSTRAINT ID_CUST_PK PRIMARY KEY (ID)
    );
CREATE TABLE IF NOT EXISTS MS_CUSTOMER_SCH.ADDRESS(
    ID INT GENERATED ALWAYS AS IDENTITY,
    STATE CHAR(2) NOT NULL,
    CITY TEXT NOT NULL,
    DISTRICT TEXT NOT NULL,
    STREET TEXT NOT NULL,
    NUMBER VARCHAR(10) NOT NULL,
    CEP CHAR(8) NOT NULL,
    COMPLEMENT TEXT NULL,
    CUSTOMER_ID INT NOT NULL,
    CONSTRAINT ID_ADDRESS_PK PRIMARY KEY (ID),
    CONSTRAINT ID_CUST_FK FOREIGN KEY (CUSTOMER_ID) REFERENCES MS_CUSTOMER_SCH.CUSTOMER(ID)
);

CREATE SCHEMA IF NOT EXISTS MS_CATALOG_SCH;
CREATE USER MS_CATALOG WITH  PASSWORD 'teste';
GRANT USAGE ON SCHEMA MS_CATALOG_SCH TO MS_CATALOG;
GRANT CONNECT ON DATABASE SHOP_STYLE TO MS_CATALOG;

CREATE TABLE IF NOT EXISTS MS_CATALOG_SCH.CATEGORY(
    ID INT GENERATED ALWAYS AS IDENTITY,
    NAME TEXT NOT NULL,
    ACTIVE BOOLEAN NOT NULL,
    PARENT_ID INT NULL,

    CONSTRAINT ID_CATEG_FK FOREIGN KEY (PARENT_ID) REFERENCES MS_CATALOG_SCH.CATEGORY(ID),
    CONSTRAINT ID_CATEG_PK PRIMARY KEY (ID)
);


CREATE TABLE IF NOT EXISTS MS_CATALOG_SCH.PRODUCT(
    ID INT GENERATED ALWAYS AS IDENTITY,
    NAME TEXT NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    BRAND TEXT NOT NULL,
    MATERIAL TEXT NOT NULL,
    ACTIVE BOOLEAN NOT NULL,
    CATEGORY_ID INT NOT NULL,

    CONSTRAINT ID_PRODCT_PK PRIMARY KEY (ID),
    CONSTRAINT ID_PRODCT_FK FOREIGN KEY (CATEGORY_ID) REFERENCES MS_CATALOG_SCH.CATEGORY(ID)
);


CREATE TABLE IF NOT EXISTS MS_CATALOG_SCH.SKU(
    ID INT GENERATED ALWAYS AS IDENTITY,
    PRICE NUMERIC NOT NULL,
    QUANTITY INT NOT NULL,
    COLOR TEXT NOT NULL,
    SIZE TEXT NOT NULL,
    HEIGHT REAL NOT NULL,
    WIDTH REAL NOT NULL,
    PRODUCT_ID INT NOT NULL,

    CONSTRAINT ID_SKU_PK PRIMARY KEY (ID),
    CONSTRAINT ID_SKU_FK FOREIGN KEY (PRODUCT_ID) REFERENCES MS_CATALOG_SCH.PRODUCT(ID)
);

CREATE TABLE IF NOT EXISTS MS_CATALOG_SCH.MEDIA(
    ID INT GENERATED ALWAYS AS IDENTITY,
    IMAGE_URL TEXT NOT NULL,
    SKU_ID INT NOT NULL,

    CONSTRAINT ID_MEDIA_FK FOREIGN KEY (SKU_ID) REFERENCES MS_CATALOG_SCH.SKU(ID),
    CONSTRAINT ID_MEDIA_PK PRIMARY KEY (ID)
);

-- TEST DATA
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Roupas', true, null);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Calças', true, 1);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Camisas', true, 1);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Bermudas', true, 1);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Sapatos', true, 2);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Jeans', true, 2);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Social', true, 3);
INSERT INTO MS_CATALOG_SCH.CATEGORY (NAME, ACTIVE, PARENT_ID) VALUES ('Esportiva', true, 2);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA MS_CATALOG_SCH TO MS_CATALOG;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA MS_CUSTOMER_SCH TO MS_CUSTOMER;


