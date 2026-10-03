BEGIN;

CREATE TABLE categories (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER',
    CONSTRAINT users_role_check
        CHECK (role IN ('CUSTOMER', 'ADMIN', 'WAREHOUSE'))
);

CREATE TABLE items (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT items_stock_check
        CHECK (stock_quantity >= 0),
    price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT items_price_check
        CHECK (price >= 0),
    category_id BIGINT NOT NULL,
    CONSTRAINT items_category_fk
        FOREIGN KEY (category_id)
        REFERENCES categories(id)
        ON DELETE RESTRICT
);

CREATE TABLE orders (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    CONSTRAINT orders_user_fk
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE RESTRICT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_packed BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE order_items (
    order_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT order_items_quantity_check
        CHECK (quantity > 0),
    unit_price NUMERIC(10, 2) NOT NULL,
    CONSTRAINT order_items_price_check
        CHECK (unit_price >= 0),
    PRIMARY KEY (order_id, item_id),
    CONSTRAINT order_items_order_fk
        FOREIGN KEY (order_id)
        REFERENCES orders(id)
        ON DELETE CASCADE,
    CONSTRAINT order_items_item_fk
        FOREIGN KEY (item_id)
        REFERENCES items(id)
        ON DELETE RESTRICT
);

INSERT INTO categories (name)
VALUES ('Mejeri'), ('Bröd'), ('Frukt och grönt');

INSERT INTO items (name, stock_quantity, price, category_id)
SELECT v.name, v.stock, v.price, c.id
FROM (VALUES
    ('Mjölk 1 liter', 10, 19.90, 'Mejeri'),
    ('Yoghurt 1 kg', 6, 24.90, 'Mejeri'),
    ('Fullkornsbröd', 8, 29.90, 'Bröd'),
    ('Äpplen, påse 1 kg', 12, 34.90, 'Frukt och grönt'),
    ('Bananer, påse 1 kg', 0, 27.90, 'Frukt och grönt')
) AS v(name, stock, price, category)
JOIN categories c ON c.name = v.category;

INSERT INTO users (name, username, password_hash, role)
VALUES
    ('Admin', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'ADMIN'),
    ('Warehouse Worker', 'warehouse', 'ae1fd358c7612a02fdc6d923fd40308ebefb0e954c7ddb6f9a8bcdd1f3b00c3b', 'WAREHOUSE'),
    ('Customer', 'customer', 'b6c45863875e34487ca3c155ed145efe12a74581e27befec5aa661b8ee8ca6dd', 'CUSTOMER');

COMMIT;
