
SET foreign_key_checks = 0;

DELETE FROM city;
DELETE FROM payment_method;
DELETE FROM `group`;
DELETE FROM group_permission;
DELETE FROM group_user;
DELETE FROM kitchen;
DELETE FROM `permission`;
DELETE FROM product;
DELETE FROM restaurant;
DELETE FROM restaurant_payment_method;
DELETE FROM restaurant_user_responsible;
DELETE FROM `state`;
DELETE FROM `user`;

SET foreign_key_checks = 1;

ALTER TABLE city            AUTO_INCREMENT = 1;
ALTER TABLE payment_method  AUTO_INCREMENT = 1;
ALTER TABLE `group`         AUTO_INCREMENT = 1;
ALTER TABLE kitchen         AUTO_INCREMENT = 1;
ALTER TABLE `permission`    AUTO_INCREMENT = 1;
ALTER TABLE product         AUTO_INCREMENT = 1;
ALTER TABLE restaurant      AUTO_INCREMENT = 1;
ALTER TABLE `state`         AUTO_INCREMENT = 1;
ALTER TABLE `user`          AUTO_INCREMENT = 1;


INSERT INTO kitchen(id, name) VALUES
(1, 'Tailandesa'),
(2, 'Indiana'),
(3, 'Argentina'),
(4, 'Brasileira')
;

INSERT INTO state (id, name) VALUES
(1, 'Minas Gerais'),
(2, 'São Paulo'),
(3, 'Ceará')
;

INSERT INTO city (id, name, state_id) VALUES
(1, 'Uberlândia', 1),
(2, 'Belo Horizonte', 1),
(3, 'São Paulo', 2),
(4, 'Campinas', 2),
(5, 'Fortaleza', 3)
;

INSERT INTO restaurant (id, name, shipping_fee, kitchen_id, active, open, date_register, date_update, address_city_id, address_cep, address_street, address_number, address_district) VALUES
(1, 'Thai Gourmet', 10, 1, true, true, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro'),
(2, 'Thai Delivery', 9.50, 1, true, true, utc_timestamp, utc_timestamp, null, null, null, null, null),
(3, 'Tuk Tuk Comida Indiana', 15, 2, true, false, utc_timestamp, utc_timestamp, null, null, null, null, null),
(4, 'Java Steakhouse', 12, 3, false, false, utc_timestamp, utc_timestamp, null, null, null, null, null),
(5, 'Lanchonete do Tio Sam', 11, 4, true, true, utc_timestamp, utc_timestamp, null, null, null, null, null),
(6, 'Bar da Maria', 6, 4, false, false, utc_timestamp, utc_timestamp, null, null, null, null, null)
;

INSERT INTO payment_method (id, description) VALUES
(1, 'Cartão de crédito'),
(2, 'Cartão de débito'),
(3, 'Dinheiro')
;

INSERT INTO permission (id, name, description) VALUES
(1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas'),
(2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO restaurant_payment_method (restaurant_id, payment_method_id) VALUES
(1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3)
;

INSERT INTO product (name, description, price, active, restaurant_id) VALUES
('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1),
('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1),
('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2),
('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3),
('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3),
('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4),
('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4),
('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5),
('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6)
;

INSERT INTO `group` (id, name) VALUES
(1, 'Gerente'),
(2, 'Vendedor'),
(3, 'Secretária'),
(4, 'Cadastrador')
;

INSERT INTO group_permission (group_id, permission_id) VALUES
(1, 1), (1, 2), (2, 1), (2, 2), (3, 1)
;

INSERT INTO user (id, name, email, password, date_register) VALUES
(1, 'João da Silva', 'joao.ger@algafood.com', '123', utc_timestamp),
(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '123', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '123', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '123', utc_timestamp),
(5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);
;

INSERT INTO group_user (group_id, user_id) VALUES
(1, 1), (2, 1), (2, 2)
;

INSERT INTO restaurant_user_responsible (user_id, restaurant_id) VALUES
(5, 1), (5, 3)
;