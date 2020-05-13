CREATE TABLE IF NOT EXISTS group_user (
  group_id       BIGINT NOT NULL,
  user_id        BIGINT NOT NULL,

  CONSTRAINT fk_gu_group_id FOREIGN KEY (group_id) REFERENCES `group` (id),
  CONSTRAINT fk_gu_user_id  FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;