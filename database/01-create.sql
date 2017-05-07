-- create schema
CREATE SCHEMA IF NOT EXISTS wlt;

-- create tables
CREATE TABLE IF NOT EXISTS wlt.product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the product',
    name VARCHAR(255) NOT NULL              COMMENT 'Name of the product',
    description VARCHAR(255)                COMMENT 'Description of the product',
    price DECIMAL                           COMMENT 'Price of the product',
    ref VARCHAR(255) UNIQUE NOT NULL        COMMENT 'UI-friendly identifier for the product',
    created_datetime DATETIME NOT NULL      COMMENT 'Datetime the product was created',
    created_user_id INTEGER                 COMMENT 'User that created the product', -- TODO: foreign key
    updated_datetime DATETIME               COMMENT 'Datetime the product was updated',
    updated_user_id INTEGER                 COMMENT 'User that updated the product', -- TODO: foreign key
    deleted_datetime DATETIME               COMMENT 'Datetime that the product was deleted',
    deleted_user_id INTEGER                 COMMENT 'User that deleted the product' -- TODO: foreign key
);

-- create user
CREATE USER 'wltapp'@'localhost' IDENTIFIED BY 'r4ttl3sn4k3s???';
GRANT SELECT ON wlt.* TO 'wltapp'@'localhost';
GRANT INSERT ON wlt.* TO 'wltapp'@'localhost';
GRANT UPDATE ON wlt.* TO 'wltapp'@'localhost';
GRANT DELETE ON wlt.* TO 'wltapp'@'localhost';
FLUSH PRIVILEGES;
