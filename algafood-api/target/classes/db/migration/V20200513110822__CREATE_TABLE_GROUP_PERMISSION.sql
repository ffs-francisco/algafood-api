CREATE TABLE IF NOT EXISTS group_permission (
  group_id       BIGINT NOT NULL,
  permission_id  BIGINT NOT NULL,

  CONSTRAINT fk_gp_group_id       FOREIGN KEY (group_id) REFERENCES `group` (id),
  CONSTRAINT fk_gp_permission_id  FOREIGN KEY (permission_id) REFERENCES permission (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;