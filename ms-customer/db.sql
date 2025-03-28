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
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA MS_CUSTOMER_SCH TO MS_CUSTOMER;


