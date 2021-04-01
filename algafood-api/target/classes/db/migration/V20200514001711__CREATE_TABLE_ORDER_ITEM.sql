CREATE TABLE IF NOT EXISTS order_item (
    id            BIGINT       NOT NULL AUTO_INCREMENT,

    price_unit    DECIMAL(10,2) NOT NULL,
    price_amount  DECIMAL(10,2) NOT NULL,
    quantity      SMALLINT(6)   NOT NULL,
    observation   VARCHAR(255)   NULL,

    order_id      BIGINT NOT NULL,
    product_id    BIGINT NOT NULL,
         
  PRIMARY KEY (id),
  UNIQUE  KEY uk_oi_order_product (order_id, product_id),

  CONSTRAINT fk_oi_order FOREIGN KEY (order_id) REFERENCES `order` (id),
  CONSTRAINT fk_oi_product FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;