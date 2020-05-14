CREATE TABLE IF NOT EXISTS product (
  id            BIGINT        NOT NULL AUTO_INCREMENT,
  name          VARCHAR(100)  NOT NULL,
  description   VARCHAR(200)  NOT NULL,
  price         DECIMAL(19,2) NOT NULL,
  active        BIT(1)        NOT NULL,

  restaurant_id  BIGINT DEFAULT NULL,   

  PRIMARY KEY (id),
  CONSTRAINT fk_p_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
