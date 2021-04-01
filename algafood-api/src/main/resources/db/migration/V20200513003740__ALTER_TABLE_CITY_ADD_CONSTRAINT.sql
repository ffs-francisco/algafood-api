ALTER TABLE city ADD CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES state (id);
