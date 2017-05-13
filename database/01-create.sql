-- create schema
CREATE SCHEMA IF NOT EXISTS wlt;

-- create tables
--CREATE TABLE IF NOT EXISTS wlt.address (
--    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the address',
--    address_line_1 VARCHAR(255) NOT NULL,
--    address_line_2 VARCHAR(255) NOT NULL,
--    town VARCHAR(255) NOT NULL,
--    county VARCHAR(255) NOT NULL,
--    country VARCHAR(255) NOT NULL,
--    post_code VARCHAR(15) NOT NULL
--);
--
--CREATE TABLE IF NOT EXISTS wlt.person (
--    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the person',
--    forename VARCHAR(255) NOT NULL,
--    surname VARCHAR(255) NOT NULL,
--    dob DATETIME NOT NULL,
--    address_id INTEGER NOT NULL,
--    FOREIGN KEY (address_id) REFERENCES wlt.address(id)
--);

CREATE TABLE IF NOT EXISTS wlt.user (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the user',
    username VARCHAR(255) UNIQUE NOT NULL ,
    enabled boolean NOT NULL DEFAULT FALSE,
    password VARCHAR(255) NOT NULL -- ,
--    person_id INTEGER NOT NULL,
--    FOREIGN KEY (person_id) REFERENCES wlt.person(id)
);

CREATE TABLE IF NOT EXISTS wlt.user_role (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the user role',
    role VARCHAR(255) UNIQUE NOT NULL,
    user_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES wlt.user(id)
);


INSERT INTO wlt.user (username, enabled, password) VALUES ('user', true, 'password');
INSERT INTO wlt.user_role (role, user_id) VALUES ('ROLE_USER', LAST_INSERT_ID());

CREATE TABLE IF NOT EXISTS wlt.product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the product',
    name VARCHAR(255) NOT NULL              COMMENT 'Name of the product',
    description VARCHAR(255)                COMMENT 'Description of the product',
    price DECIMAL                           COMMENT 'Price of the product',
    ref VARCHAR(255) UNIQUE NOT NULL        COMMENT 'UI-friendly identifier for the product', -- indexed due to 'unique' clause
    created_datetime DATETIME NOT NULL      COMMENT 'Datetime the product was created',
    created_user_id INTEGER                 COMMENT 'User that created the product',
    updated_datetime DATETIME               COMMENT 'Datetime the product was updated',
    updated_user_id INTEGER                 COMMENT 'User that updated the product',
    deleted_datetime DATETIME               COMMENT 'Datetime that the product was deleted',
    deleted_user_id INTEGER                 COMMENT 'User that deleted the product' -- ,
--    FOREIGN KEY (created_user_id) REFERENCES wlt.user(id),
--    FOREIGN KEY (updated_user_id) REFERENCES wlt.user(id),
--    FOREIGN KEY (deleted_user_id) REFERENCES wlt.user(id)
) COMMENT 'Core details for a product';



-- add in an initial admin user
-- TODO: insert into user...


-- create DB user
CREATE USER 'wltapp'@'localhost' IDENTIFIED BY 'r4ttl3sn4k3s???';
GRANT SELECT ON wlt.* TO 'wltapp'@'localhost';
GRANT INSERT ON wlt.* TO 'wltapp'@'localhost';
GRANT UPDATE ON wlt.* TO 'wltapp'@'localhost';
GRANT DELETE ON wlt.* TO 'wltapp'@'localhost';
FLUSH PRIVILEGES;
