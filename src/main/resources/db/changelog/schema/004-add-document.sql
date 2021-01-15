CREATE TABLE document (
	id uuid NOT NULL,
    name varchar(100),
	CONSTRAINT pk_document PRIMARY KEY (id)
);

ALTER TABLE owner ADD COLUMN document_id uuid;
ALTER TABLE owner ADD CONSTRAINT fk_document_id_to_document FOREIGN KEY (document_id) REFERENCES document(id);