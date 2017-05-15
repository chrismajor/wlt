-- create schema
CREATE SCHEMA IF NOT EXISTS wlt;

-- create tables
CREATE TABLE IF NOT EXISTS wlt.address (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the address',
    address_line_1 VARCHAR(255) NOT NULL    COMMENT 'First line of address',
    address_line_2 VARCHAR(255) NOT NULL    COMMENT 'Second line of address',
    town VARCHAR(255) NOT NULL              COMMENT 'Town / city',
    county VARCHAR(255) NOT NULL            COMMENT 'County / state',
    country VARCHAR(255) NOT NULL           COMMENT 'Country',
    post_code VARCHAR(15) NOT NULL          COMMENT 'Post code / zip code'
) COMMENT 'Postal addresses';

CREATE TABLE IF NOT EXISTS wlt.person (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the person',
    forename VARCHAR(255) NOT NULL          COMMENT 'First name',
    surname VARCHAR(255) NOT NULL           COMMENT 'Last name',
    dob DATETIME NOT NULL                   COMMENT 'Date of birth',
    address_id INTEGER NOT NULL             COMMENT 'Foreign key for address table',
    FOREIGN KEY (address_id) REFERENCES wlt.address(id)
) COMMENT 'Personal details';

CREATE TABLE IF NOT EXISTS wlt.user (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the user',
    username VARCHAR(255) UNIQUE NOT NULL   COMMENT 'Username / email address',
    enabled boolean NOT NULL DEFAULT FALSE  COMMENT 'Flag to denote whether the account is active',
    password VARCHAR(255) NOT NULL          COMMENT 'Password - hashed and salted',
    person_id INTEGER NOT NULL              COMMENT 'Foreign key for person table',
    FOREIGN KEY (person_id) REFERENCES wlt.person(id)
) COMMENT 'User account details';

CREATE TABLE IF NOT EXISTS wlt.role (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the role',
    name VARCHAR(255) UNIQUE NOT NULL       COMMENT 'Name of the role'
) COMMENT 'Roles that a user can have';

CREATE TABLE IF NOT EXISTS wlt.user_role (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the user role',
    user_id INTEGER NOT NULL                COMMENT 'Foreign key for user table',
    role_id INTEGER NOT NULL                COMMENT 'Foreign key for role table',
    UNIQUE (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES wlt.user(id),
    FOREIGN KEY (role_id) REFERENCES wlt.role(id)
) COMMENT 'Table for joining user to role tables';

CREATE TABLE IF NOT EXISTS wlt.product (
    id INTEGER PRIMARY KEY AUTO_INCREMENT   COMMENT 'ID for the product',
    name VARCHAR(255) NOT NULL              COMMENT 'Name of the product',
    description VARCHAR(255)                COMMENT 'Description of the product',
    price DECIMAL                           COMMENT 'Price of the product',
    ref VARCHAR(255) UNIQUE NOT NULL        COMMENT 'UI-friendly identifier for the product', -- indexed due to 'unique' clause
    created_datetime DATETIME NOT NULL      COMMENT 'Datetime the product was created',
    deleted_datetime DATETIME               COMMENT 'Datetime that the product was deleted'
) COMMENT 'Core details for a product';


-- add in the roles we currently allow
INSERT INTO wlt.role (name) VALUES ('ROLE_USER');
INSERT INTO wlt.role (name) VALUES ('ROLE_ADMIN');


-- create DB user
CREATE USER 'wltapp'@'localhost' IDENTIFIED BY 'r4ttl3sn4k3s???';
GRANT SELECT ON wlt.* TO 'wltapp'@'localhost';
GRANT INSERT ON wlt.* TO 'wltapp'@'localhost';
GRANT UPDATE ON wlt.* TO 'wltapp'@'localhost';
GRANT DELETE ON wlt.* TO 'wltapp'@'localhost';
FLUSH PRIVILEGES;
