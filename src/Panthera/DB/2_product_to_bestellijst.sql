CREATE TABLE product_to_bestellijst
(
  id serial NOT NULL,
  product_id integer,
  bestellijst_id integer,
  CONSTRAINT product_to_bestellijst_pkey PRIMARY KEY (id),
  CONSTRAINT product_to_bestellijst_bestellijst_id_fkey FOREIGN KEY (bestellijst_id)
      REFERENCES bestellijst (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT product_to_bestellijst_product_id_fkey FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);