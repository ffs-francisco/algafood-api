-------------------------
----- DATAS OF TEST -----
-------------------------

INSERT INTO kitchen(id, name) VALUES (1, 'Tailandesa');
INSERT INTO kitchen(id, name) VALUES (2, 'Indiana');
INSERT INTO kitchen(id, name) VALUES (3, 'Argentina');
INSERT INTO kitchen(id, name) VALUES (4, 'Brasileira');


INSERT INTO state (id, name) VALUES (1, 'Minas Gerais');
INSERT INTO state (id, name) VALUES (2, 'São Paulo');
INSERT INTO state (id, name) VALUES (3, 'Ceará');


INSERT INTO city (id, name, state_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO city (id, name, state_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO city (id, name, state_id) VALUES (3, 'São Paulo', 2);
INSERT INTO city (id, name, state_id) VALUES (4, 'Campinas', 2);
INSERT INTO city (id, name, state_id) VALUES (5, 'Fortaleza', 3);


INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update, address_city_id, address_cep, address_street, address_number, address_district) VALUES (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update) VALUES (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update) VALUES (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update) VALUES (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update) VALUES (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, date_register, date_update) VALUES (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);


INSERT INTO form_payment (id, description) VALUES (1, 'Cartão de crédito');
INSERT INTO form_payment (id, description) VALUES (2, 'Cartão de débito');
INSERT INTO form_payment (id, description) VALUES (3, 'Dinheiro');


INSERT INTO permission (id, name, description) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permission (id, name, description) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');


INSERT INTO restaurant_form_payment (restaurant_id, form_payment_id) VALUES (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);


INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
INSERT INTO product (name, description, price, active, restaurant_id) VALUES ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);