UPDATE city C SET C.state_id = (SELECT E.id FROM state E WHERE E.name = C.name_state);
