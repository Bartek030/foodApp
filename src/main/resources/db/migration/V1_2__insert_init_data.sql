INSERT INTO address (country, city, street, number, zip_code)
VALUES
('Polska', 'Kraków', 'Mickiewicza', '2A', '38-300'),
('Polska', 'Warszawa', 'Długa', '5', '34-300'),
('Polska', 'Gdańsk', 'Lewa', '1', '32-300'),
('Polska', 'Wrocław', 'Prawa', '22', '39-300');

INSERT INTO food_app_user (name, surname, email, phone, address_id)
VALUES
('TESTname', 'TESTsurname', 'testemail@email.com', '+48 555555555', 2);

INSERT INTO delivery_address (country, city, street)
VALUES
('Polska', 'Kraków', 'Mickiewicza'),
('Polska', 'Warszawa', 'Długa'),
('Polska', 'Gdańsk', 'Lewa'),
('Polska', 'Wrocław', 'Prawa');

INSERT INTO restaurant (name, food_app_user_id, address_id)
VALUES
('KEBAB', 1, 3),
('PIZZA', 1, 4);

INSERT INTO menu (name, category, restaurant_id)
VALUES
('kebab menu', 'kurczak', 1),
('kebab menu2', 'wołowinka', 1);

INSERT INTO food (name, price, description, menu_id)
VALUES
('kebab mały', 20.50, 'Malutki kebab z kurczaka', 1),
('kebab duży', 25.50, 'Duży kebab z kurczaka', 1);
