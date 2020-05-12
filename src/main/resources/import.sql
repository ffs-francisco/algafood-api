INSERT INTO kitchen(id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen(id, name) VALUES (2, 'Indiana');

INSERT INTO state (id, name) VALUES (1, 'Minas Gerais');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');
INSERT INTO state (id, name) VALUES (3, 'Ceará');

INSERT INTO city (id, name, state_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO city (id, name, state_id) VALUES (3, 'São Paulo', 2);
INSERT INTO city (id, name, state_id) VALUES (4, 'Campinas', 2);
INSERT INTO city (id, name, state_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, address_city_id, address_cep, address_street, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id) VALUES (2, 'Thai Delivery', 9.50, 1);
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id) VALUES (3, 'Tuk Tuk Comida Indiana', 15, 2);

INSERT INTO form_payment (id, description) VALUES (1, 'Cartão de crédito');
INSERT INTO form_payment (id, description) VALUES (2, 'Cartão de débito');
INSERT INTO form_payment (id, description) VALUES (3, 'Dinheiro');

INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurant_form_payment (restaurant_id, form_payment_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);