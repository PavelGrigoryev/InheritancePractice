--liquibase formatted sql

--changeset Grigoryev_Pavel:2
INSERT INTO payment (payment_from, payment_to, payment_type)
VALUES ('Alice', 'Bob', 'ERIP'),
       ('Charlie', 'David', 'REQUISITE'),
       ('Eve', 'Frank', 'ERIP'),
       ('Grace', 'Harry', 'REQUISITE'),
       ('Isaac', 'Jack', 'ERIP'),
       ('Karen', 'Leo', 'REQUISITE'),
       ('Mia', 'Noah', 'ERIP'),
       ('Olivia', 'Peter', 'REQUISITE'),
       ('Quinn', 'Ryan', 'ERIP'),
       ('Sophia', 'Tom', 'REQUISITE');

INSERT INTO payment_by_erip (id, field_for_insertion, value_for_insertion)
VALUES (1, 'Service code', '123456'),
       (3, 'Service code', '789012'),
       (5, 'Service code', '345678'),
       (7, 'Service code', '901234'),
       (9, 'Service code', '567890');

INSERT INTO payment_by_requisite (id, unp, receiver, destination)
VALUES (2, '123456789', 'David LLC', 'Payment for goods'),
       (4, '987654321', 'Charlie Inc.', 'Payment for services'),
       (6, '456789123', 'Frank Ltd.', 'Payment for rent'),
       (8, '321987654', 'Eve Co.', 'Payment for salary'),
       (10, '789123456', 'Tom LLC', 'Payment for taxes');
