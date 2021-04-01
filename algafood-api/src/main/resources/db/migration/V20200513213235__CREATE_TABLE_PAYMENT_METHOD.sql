CREATE TABLE IF NOT EXISTS payment_method (
  id            BIGINT        NOT NULL AUTO_INCREMENT,
  description   VARCHAR(100)  NOT NULL,  

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;