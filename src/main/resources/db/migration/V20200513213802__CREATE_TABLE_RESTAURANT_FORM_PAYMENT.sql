CREATE TABLE IF NOT EXISTS restaurant_form_payment (
  restaurant_id      BIGINT NOT NULL,
  form_payment_id    BIGINT NOT NULL,

  CONSTRAINT fk_rfp_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
  CONSTRAINT fk_rfp_form_payment_id FOREIGN KEY (form_payment_id) REFERENCES form_payment (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;