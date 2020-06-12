CREATE TABLE IF NOT EXISTS restaurant_payment_method (
  restaurant_id        BIGINT NOT NULL,
  payment_method_id    BIGINT NOT NULL,

  CONSTRAINT fk_rpm_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
  CONSTRAINT fk_rpm_payment_method_id FOREIGN KEY (payment_method_id) REFERENCES payment_method (id),
  CONSTRAINT uk_rpm_restaurant_payment_method UNIQUE (restaurant_id, payment_method_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;