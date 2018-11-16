/*DROP DATABASE IF EXISTS rent_car;
CREATE DATABASE rent_car OWNER postgres ENCODING 'utf8';
GRANT ALL PRIVILEGES ON DATABASE rent_car TO postgres;
\connect rent_car;*/

SET search_path TO rent_car;

DROP TABLE IF EXISTS rent;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS lorry;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS color;
DROP TABLE IF EXISTS model;
DROP TABLE IF EXISTS manufacturer;
DROP TABLE IF EXISTS person_data;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS driving_license;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS street;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;

DROP TYPE IF EXISTS gender_state;
DROP TYPE IF EXISTS transmission_type;
DROP TYPE IF EXISTS driving_category;

DROP SCHEMA IF EXISTS rent_car CASCADE;
CREATE SCHEMA rent_car;


/* Execute below query explicitly because it is somehow skipped when all queries are selected and marked to run. */
CREATE TYPE gender_state AS ENUM (
    'MALE', 'FEMALE', 'UNSURE_YET', 'TRANSGENDERED_TO_MALE', 'TRANSGENDERED_TO_FEMALE'
);
CREATE TYPE driving_category AS ENUM ('A', 'AM', 'B', 'C', 'D', 'E');
CREATE TYPE transmission_type AS ENUM ('AUTOMATIC', 'MECHANIC');

CREATE TABLE IF NOT EXISTS manufacturer (
    id SERIAL PRIMARY KEY,
    manufacturer VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS model (
    id SERIAL PRIMARY KEY,
    manufacturer_id INTEGER NOT NULL REFERENCES manufacturer (id),
    model VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS color (
    id SERIAL PRIMARY KEY,
    color VARCHAR(25) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(1024) DEFAULT NULL,
    model_id INTEGER NOT NULL REFERENCES model (id),
    color_id INTEGER NOT NULL REFERENCES color (id),
    transmission transmission_type NOT NULL,
    produced_year SMALLINT NOT NULL,
    full_price INTEGER NOT NULL,
    day_price INTEGER NOT NULL,
    created_date DATE NOT NULL,
    updated_date DATE DEFAULT NULL,
    comment VARCHAR(1024) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS car (
    id BIGSERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL REFERENCES vehicle (id),
    passengers_capacity SMALLINT NOT NULL,
    trunk_capacity SMALLINT NOT NULL
);

CREATE TABLE IF NOT EXISTS lorry (
    id BIGSERIAL PRIMARY KEY,
    vehicle_id BIGINT NOT NULL REFERENCES vehicle (id),
    carrying_capacity INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS country (
    id SERIAL PRIMARY KEY,
    country VARCHAR(25) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS city (
    id BIGSERIAL PRIMARY KEY,
    country_id INTEGER NOT NULL REFERENCES country (id),
    city VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS street (
    id BIGSERIAL PRIMARY KEY,
    city_id BIGINT NOT NULL REFERENCES city (id),
    street VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS address (
    id BIGSERIAL PRIMARY KEY,
    street_id BIGINT NOT NULL REFERENCES street (id),
    building VARCHAR(10) NOT NULL,
    flat VARCHAR(5) NOT NULL
);

CREATE TABLE IF NOT EXISTS driving_license (
    id BIGSERIAL PRIMARY KEY,
    serial_number VARCHAR(10) UNIQUE NOT NULL,
    issued_date DATE NOT NULL,
    expire_date DATE NOT NULL,
    category driving_category NOT NULL
);

CREATE TABLE IF NOT EXISTS role (
    id SERIAL PRIMARY KEY,
    role VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
    id BIGSERIAL PRIMARY KEY,
    role_id INTEGER NOT NULL DEFAULT '2' REFERENCES role (id),
    login VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(25) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_date DATE NOT NULL,
    updated_date DATE DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS person_data (
    id BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL UNIQUE REFERENCES person (id),
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    middle_name VARCHAR(20) DEFAULT NULL,
    birth_date DATE NOT NULL,
    gender gender_state NOT NULL,
    passport VARCHAR(9) UNIQUE NOT NULL,
    address_id BIGINT DEFAULT NULL REFERENCES address (id),
    driving_license_id BIGINT DEFAULT NULL REFERENCES driving_license (id)
);

CREATE TABLE IF NOT EXISTS rent (
    id BIGSERIAL,
    vehicle_id BIGINT NOT NULL REFERENCES vehicle (id),
    person_id BIGINT NOT NULL REFERENCES person (id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    cost INTEGER NOT NULL,
    paid BOOLEAN NOT NULL,
    created_date DATE NOT NULL,
    updated_date DATE DEFAULT NULL,
    comment VARCHAR(1024) DEFAULT NULL,
    PRIMARY KEY (vehicle_id, person_id)
);


INSERT INTO manufacturer (manufacturer) VALUES
    ('Suzuki'),
    ('Toyota'),
    ('Nissan'),
    ('Ford'),
    ('БелАЗ');

INSERT INTO model (manufacturer_id, model) VALUES
    ('1', 'SX4'),
    ('2', 'RAV4'),
    ('3', 'Qashqai'),
    ('4', 'Focus'),
    ('5', 'БелАЗ-75710');

INSERT INTO color (color) VALUES
    ('красный'),
    ('синий'),
    ('белый'),
    ('черный'),
    ('жёлтый');

INSERT INTO vehicle (model_id, transmission, color_id, produced_year, full_price, day_price, created_date) VALUES
    (5, 'MECHANIC', 5, 2013, 100000, 500, '2013-12-09'),
    (1, 'AUTOMATIC', 3, 2010, 7000, 35, '2010-01-12'),
    (1, 'MECHANIC', 2, 2009, 6500, 30, '2009-03-25'),
    (2, 'MECHANIC', 3, 2012, 10000, 50, '2012-02-16'),
    (3, 'AUTOMATIC', 2, 2000, 5000, 25, '2000-07-19'),
    (4, 'AUTOMATIC', 2, 2013, 6000, 30, '2013-04-15'),
    (2, 'MECHANIC', 3, 2005, 9000, 45, '2005-08-14'),
    (3, 'MECHANIC', 2, 2001, 5200, 25, '2001-10-25'),
    (2, 'AUTOMATIC', 1, 2015, 18000, 90, '2015-11-20');

INSERT INTO lorry (vehicle_id, carrying_capacity) VALUES
    (1, 450000);

INSERT INTO car (vehicle_id, passengers_capacity, trunk_capacity) VALUES
    (2, 4, 300),
    (3, 4, 350),
    (4, 4, 350),
    (5, 4, 380),
    (6, 4, 250),
    (7, 4, 380),
    (8, 4, 300),
    (9, 4, 380);

INSERT INTO country (country) VALUES
    ('Belarus'),
    ('Russia');

INSERT INTO city (country_id, city) VALUES
    (1, 'Минск'),
    (1, 'Витебск'),
    (2, 'Москва'),
    (2, 'Санкт-Петербург');

INSERT INTO street (city_id, street) VALUES
    (2, 'ул. Ленина'),
    (3, 'ул. Ленина'),
    (3, 'ул. Строителей'),
    (4, 'ул. Строителей'),
    (4, 'ул. Садовая');

INSERT INTO address (street_id, building, flat) VALUES
    (1, '20', '12'),
    (2, '103', '14'),
    (3, '58', '121'),
    (4, '19', '5'),
    (5, '2/2', '10'),
    (1, '20A', '15');

INSERT INTO driving_license (serial_number, issued_date, expire_date, category) VALUES
    ('AA123123', '2010-07-04', '2020-07-06', 'B'),
    ('BB987987', '2008-12-14', '2018-12-15', 'B'),
    ('CC123123', '2014-10-14', '2024-10-13', 'B'),
    ('DD987987', '2011-05-10', '2021-05-09', 'C'),
    ('EE987987', '2011-08-17', '2021-08-16', 'B'),
    ('FF987987', '2013-09-20', '2023-09-19', 'B'),
    ('EE111222', '2015-02-21', '2025-02-20', 'C');

INSERT INTO role (role) VALUES
    ('Администратор'),
    ('Пользователь');

INSERT INTO person (login, password, email, role_id, created_date) VALUES
    ('ivan1971', 'ivan1971pass', 'ivan1971@tut.by', 2, '2018-10-20'),
    ('hannapavlova', 'pavlovahanna', 'hanna-flower@yandex.ru', 2, '2018-10-21'),
    ('root', 'pass', 'superment79@tut.by', 2, '2018-10-24'),
    ('rediska', 'olga1981', 'olga1981@mail.ru', 2, '2018-10-24'),
    ('fiona', 'password', 'fiona-soup4ik@tut.by', 2, '2018-10-24'),
    ('korol', 'korolyov', 'akorolyov@epam.com', 1, '2018-10-25'),
    ('engbart', 'engpassword', 'engbart@gmail.com', 1, '2018-10-25');

INSERT INTO person_data (person_id, first_name, last_name, middle_name, birth_date, gender, passport, address_id, driving_license_id) VALUES
    (1, 'Юрий', 'Иванченко', NULL, '1971-12-15','MALE', 'AB123456', 1, 1),
    (2, 'Анна', 'Павлова', NULL, '1980-01-11', 'FEMALE', 'AB9876543', 2, 2),
    (3, 'Сергей', 'Волков', NULL, '1979-03-10', 'UNSURE_YET', 'BM123456', 3, 3),
    (4, 'Ольга', 'Полтавская', NULL, '1981-01-11', 'TRANSGENDERED_TO_MALE', 'BM9876543', 4, 4),
    (5, 'Фиона', 'Борщевская', NULL, '1962-04-04', 'TRANSGENDERED_TO_FEMALE', 'HB9876543', 5, 5),
    (6, 'Андрей', 'Королев', NULL, '1981-02-15', 'MALE', 'HB123456', 6, 6),
    (7, 'Bart', 'Simpson', NULL, '1981-02-15', 'UNSURE_YET', 'EE123456', 6, 7);

INSERT INTO rent (vehicle_id, person_id, start_date, end_date, cost, paid, created_date) VALUES
    (1, 1, '2018-04-04', '2018-04-08', 100, TRUE, '2018-10-20'),
    (1, 2, '2018-02-08', '2018-02-24', 220, TRUE, '2018-10-21'),
    (3, 3, '2018-06-01', '2018-07-01', 80, TRUE, '2018-10-24'),
    (1, 4, '2018-03-07', '2018-03-09', 90, TRUE, '2018-10-24'),
    (4, 5, '2018-11-02', '2018-12-04', 120, TRUE, '2018-10-25'),
    (3, 5, '2018-10-10', '2018-12-30', 150, TRUE, '2018-10-25');

SELECT * FROM vehicle;
SELECT * FROM car;
SELECT * FROM lorry;
SELECT * FROM color;
SELECT * FROM manufacturer;
SELECT * FROM model;
SELECT * FROM country;
SELECT * FROM city;
SELECT * FROM street;
SELECT * FROM address;
SELECT * FROM driving_license;
SELECT * FROM person;
SELECT * FROM person_data;
SELECT * FROM role;
SELECT * FROM rent;
