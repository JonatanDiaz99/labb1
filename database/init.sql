CREATE TABLE T_ITEM (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    price NUMERIC(10,2) NOT NULL
);

INSERT INTO T_ITEM (name, quantity, price)
VALUES
    ('Mjölk', 10, 19.90),
    ('Köttfärs', 5, 89.90),
    ('Olivolja', 8, 119.90);