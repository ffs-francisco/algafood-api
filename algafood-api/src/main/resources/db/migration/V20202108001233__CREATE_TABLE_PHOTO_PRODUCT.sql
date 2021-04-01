CREATE TABLE IF NOT EXISTS product_photo (
  product_id         BIGINT NOT NULL,
  size               INT    NOT NULL,

  content_type       VARCHAR(80)  NOT NULL,
  description        VARCHAR(150) NOT NULL,
  file_name          VARCHAR(150) NOT NULL,

  PRIMARY KEY (product_id),
  CONSTRAINT fk_pp_product_photo_id FOREIGN KEY (product_id) REFERENCES product (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
