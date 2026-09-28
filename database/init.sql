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

COMMIT;