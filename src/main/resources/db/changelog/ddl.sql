--liquibase formatted sql

--changeset Grigoryev_Pavel:1
CREATE TABLE IF NOT EXISTS payment
(
    id           BIGSERIAL PRIMARY KEY,
    payment_from VARCHAR(50) NOT NULL,
    payment_to   VARCHAR(50) NOT NULL,
    payment_type VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_by_erip
(
    id                  BIGINT PRIMARY KEY REFERENCES payment (id),
    field_for_insertion VARCHAR(50) NOT NULL,
    value_for_insertion VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS payment_by_requisite
(
    id          BIGINT PRIMARY KEY REFERENCES payment (id),
    unp         VARCHAR(50) NOT NULL,
    receiver    VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL
);
