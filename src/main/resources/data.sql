
INSERT INTO shopping.users (name, phone_number, email, password, gender, social_media_profiles, date_of_birth)
VALUES ('Hotae ', '+15555655', 'hota@example.com', 'password', 'FEMALE', 'https://www.facebook.com/hota', '1996-01-01');

INSERT INTO shopping.users (name, phone_number, email, password, gender, social_media_profiles, date_of_birth)
VALUES ('Kony ', '552555655', 'Kony@example.com', 'password', 'MALE', 'https://www.facebook.com/kony', '832-01-01');

INSERT INTO shopping.address (state, contact_phone_number, city, street, post_code, user_id)
VALUES ('California', '123-456-7890', 'San Francisco', '123 Main Street', '94105', 1);
INSERT INTO shopping.seller (user_id) VALUES(1);
INSERT INTO shopping.stores (name, description, seller_id) VALUES ('My Store', 'This is my store.', 1);
INSERT INTO shopping.products (name, description, image, price, amount, store_id) VALUES ('BAN', 'no thing',null , 42, 2,1);
INSERT INTO shopping.buyer (user_id) VALUES (1);