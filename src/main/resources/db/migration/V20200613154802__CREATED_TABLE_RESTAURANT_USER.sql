CREATE TABLE IF NOT EXISTS restaurant_user_responsible (
  user_id            BIGINT NOT NULL,
  restaurant_id      BIGINT NOT NULL,

  CONSTRAINT fk_rur_user_id FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT fk_rur_restaurant_id FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
  CONSTRAINT uk_rur_restaurant_user UNIQUE (restaurant_id, user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
