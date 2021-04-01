CREATE TABLE IF NOT EXISTS restaurant (
  id            BIGINT        NOT NULL AUTO_INCREMENT,
  name          VARCHAR(100)  NOT NULL,
  shipping_fee  DECIMAL(19,2) NOT NULL,
   
  address_cep         VARCHAR(100) DEFAULT NULL,
  address_district    VARCHAR(100) DEFAULT NULL,
  address_street      VARCHAR(100) DEFAULT NULL,
  address_number      VARCHAR(100) DEFAULT NULL,
  address_complement  VARCHAR(100) DEFAULT NULL,

  address_city_id  BIGINT DEFAULT NULL,   
  kitchen_id       BIGINT NOT NULL,

  date_register DATETIME NOT NULL,
  date_update   DATETIME NOT NULL,

  PRIMARY KEY (id),
  CONSTRAINT fk_r_kitchen_id FOREIGN KEY (kitchen_id) REFERENCES kitchen (id),
  CONSTRAINT fk_r_address_city_id FOREIGN KEY (address_city_id) REFERENCES city (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;