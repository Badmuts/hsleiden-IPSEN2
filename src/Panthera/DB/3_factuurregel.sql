CREATE TABLE factuurregel(
  id SERIAL PRIMARY KEY,
  factuur_id INT NOT NULL REFERENCES factuur(id),
  aantal INT NOT NULL,
  product_id INT NOT NULL REFERENCES product(id)
);
