CREATE TABLE IF NOT EXISTS form_payment (
  id            BIGINT        NOT NULL AUTO_INCREMENT,
  description   VARCHAR(100)  NOT NULL,  

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

