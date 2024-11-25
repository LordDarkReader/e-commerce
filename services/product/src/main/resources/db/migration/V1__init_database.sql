create table if not exists category(
    ID INTEGER NOT NULL PRIMARY KEY ,
    DESCRIPTION VARCHAR(255),
    NAME VARCHAR(255)
);

create table if not exists product(
                                      ID INTEGER NOT NULL PRIMARY KEY ,
                                      DESCRIPTION VARCHAR(255),
                                      NAME VARCHAR(255),
                                      AVAILABLE_QUANTITY DOUBLE PRECISION NOT NULL,
                                      PRICE NUMERIC(38, 2),
                                      CATEGORY_ID INTEGER CONSTRAINT FK1DFSFSDFF REFERENCES category
);

CREATE SEQUENCE IF NOT EXISTS CATEGORY_SEQ INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS PRODUCT_SEQ INCREMENT BY 50;