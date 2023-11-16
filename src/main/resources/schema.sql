create schema if not exists shopping;

CREATE TABLE if NOT EXISTS  shopping.users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL,
    social_media_profiles VARCHAR(255),
    date_of_birth DATE,
    created_at DATE DEFAULT CURRENT_DATE
);
CREATE TABLE if NOT EXISTS shopping.address (
    address_id SERIAL PRIMARY KEY,
    state VARCHAR(255) NOT NULL,
    contact_phone_number VARCHAR(255) ,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    post_code VARCHAR(255) NOT NULL,
    created_at DATE  DEFAULT CURRENT_DATE,
    user_id INTEGER NOT NULL REFERENCES shopping.users(user_id) ON DELETE CASCADE
);
CREATE TABLE if NOT EXISTS  shopping.seller (
  seller_id SERIAL PRIMARY KEY,
  created_at DATE DEFAULT CURRENT_DATE,
  user_id INT REFERENCES shopping.users(user_id) ON DELETE CASCADE,
  CONSTRAINT fk_seller_user FOREIGN KEY (user_id) REFERENCES shopping.users(user_id) ON DELETE CASCADE
);

CREATE TABLE if NOT EXISTS  shopping.stores (
    store_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    updated_at DATE NOT NULL DEFAULT CURRENT_DATE,
    seller_id INT NOT NULL REFERENCES shopping.seller(seller_id)
);

CREATE TABLE if NOT EXISTS  shopping.products (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image VARCHAR(255),
    price NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount NUMERIC(10,2) NOT NULL,
    store_id INT not null,
    FOREIGN KEY (store_id) REFERENCES shopping.stores (store_id)
);
CREATE TABLE if NOT EXISTS  shopping.buyer (
    buyer_id SERIAL PRIMARY KEY,
    user_id INT REFERENCES shopping.users(user_id),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE
);
CREATE TABLE if NOT EXISTS  shopping.orders (
    order_number SERIAL PRIMARY KEY,
    total_price DOUBLE PRECISION NOT NULL,
    create_at TIMESTAMPTZ NOT NULL DEFAULT  now() ,
    order_status VARCHAR(255) ,
    buyer_id INT NOT NULL REFERENCES shopping.buyer(buyer_id)
);
CREATE TABLE if NOT EXISTS  shopping.order_items (
    item_id SERIAL PRIMARY KEY,
    price DOUBLE PRECISION NOT NULL,
    quantity INTEGER NOT NULL,
    discount DOUBLE PRECISION ,
    tax DOUBLE PRECISION ,
    create_at TIMESTAMP NOT NULL DEFAULT now(),
    product_id INTEGER REFERENCES shopping.products (product_id),
    order_number INTEGER REFERENCES shopping.orders (order_number)
);