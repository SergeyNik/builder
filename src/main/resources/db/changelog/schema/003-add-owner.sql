CREATE TABLE owner (
	id uuid NOT NULL,
    name varchar(100),
	CONSTRAINT pk_owner PRIMARY KEY (id)
);

ALTER TABLE car ADD COLUMN owner_id uuid;
ALTER TABLE car ADD CONSTRAINT fk_owner_id_to_owner FOREIGN KEY (owner_id) REFERENCES owner(id);