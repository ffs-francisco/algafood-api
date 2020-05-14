CREATE TABLE IF NOT EXISTS `order` (
    id  BIGINT    NOT NULL AUTO_INCREMENT,

    sub_total     DECIMAL(10,2) NOT NULL,
    shipping_fee  DECIMAL(10,2) NOT NULL,
    amount        DECIMAL(10,2) NOT NULL,
    
    status VARCHAR(20) NOT NULL,

    address_cep         VARCHAR(100) DEFAULT NULL,
    address_district    VARCHAR(100) DEFAULT NULL,
    address_street      VARCHAR(100) DEFAULT NULL,
    address_number      VARCHAR(100) DEFAULT NULL,
    address_complement  VARCHAR(100) DEFAULT NULL,

    date_register      DATETIME NOT NULL,
    date_confirmation  DATETIME NULL,
    date_cancellation  DATETIME NULL,
    date_delivery      DATETIME NULL,

    restaurant_id     BIGINT NOT NULL,
    form_payment_id   BIGINT NOT NULL,
    customer_user_id  BIGINT NOT NULL,
         
  PRIMARY KEY (id),
  constraint fk_o_restaurant    foreign key (restaurant_id) references restaurant (id),
  constraint fk_o_form_payment  foreign key (form_payment_id) references form_payment (id),
  constraint fk_o_customer_user foreign key (customer_user_id) references user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
