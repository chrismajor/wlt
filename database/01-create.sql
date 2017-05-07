-- create schema
CREATE SCHEMA IF NOT EXISTS wlt;

-- create tables
CREATE TABLE IF NOT EXISTS wlt.product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the product',
    name VARCHAR(255) NOT NULL              COMMENT 'Name of the product',
    description VARCHAR(255)                COMMENT 'Description of the product',
    price DECIMAL                           COMMENT 'Price of the product',
    ref VARCHAR(255) UNIQUE NOT NULL        COMMENT 'UI-friendly identifier for the product',
    createdDatetime DATETIME NOT NULL       COMMENT 'Datetime the product was created',
    createdUserId INTEGER                   COMMENT 'User that created the product', -- TODO: foreign key
    updatedUserId INTEGER                   COMMENT 'User that updated the product', -- TODO: foreign key
    deletedTimestamp DATETIME               COMMENT 'Datetime that the product was deleted'
);

-- create user
CREATE USER 'wltapp'@'localhost' IDENTIFIED BY 'r4ttl3sn4k3s???';
GRANT SELECT ON wlt.* TO 'wltapp'@'localhost';
GRANT INSERT ON wlt.* TO 'wltapp'@'localhost';
GRANT UPDATE ON wlt.* TO 'wltapp'@'localhost';
GRANT DELETE ON wlt.* TO 'wltapp'@'localhost';
FLUSH PRIVILEGES;
