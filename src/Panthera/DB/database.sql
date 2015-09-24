/**
 * Land table
 */
CREATE TABLE land (
	id SERIAL NOT NULL PRIMARY KEY,
	land VARCHAR NOT NULL
);

/**
 * Debiteur table
 */
 CREATE TABLE debiteur (
 	id SERIAL NOT NULL PRIMARY KEY,
 	aanhef VARCHAR NOT NULL,
 	voornaam VARCHAR NOT NULL,
 	tussenvoegesel VARCHAR,
 	naam VARCHAR NOT NULL,
 	adres VARCHAR NOT NULL,
 	woonplaats VARCHAR NOT NULL,
 	postcode VARCHAR NOT NULL,
 	email VARCHAR NOT NULL,
 	telefoon int,
 	land_id int REFERENCES land (id)

 );

/**
 * Factuur table
 */
CREATE TABLE factuur (

	id SERIAL NOT NULL PRIMARY KEY,
	debiteur_id int NOT NULL REFERENCES debiteur (id),
	factuurnummer int NOT NULL,
	factuurdatum DATE NOT NULL,
	vervaldatum DATE NOT NULL,
	status VARCHAR NOT NULL

);

/**
* Organisatie table
*/
CREATE TABLE organisatie (

	id SERIAL NOT NULL PRIMARY KEY,
	bedrijfsnaam VARCHAR NOT NULL,
	adres VARCHAR NOT NULL,
	woonplaats VARCHAR NOT NULL,
	postcode VARCHAR NOT NULL,
	email VARCHAR NOT NULL,
	telefoon int,
	kvk int NOT NULL,
	btw_nummer VARCHAR NOT NULL,
	iban VARCHAR NOT NULL,
	bic VARCHAR NOT NULL,
	land_id int NOT NULL REFERENCES land (id)
);

CREATE TABLE notitie (
id int NOT NULL PRIMARY KEY,
titel VARCHAR NOT NULL,
notitie TEXT NOT NULL
);

CREATE TABLE mail (
id int NOT NULL PRIMARY KEY,
debiteur_id int NOT NULL REFERENCES debiteur (id),
factuur_id int NOT NULL REFERENCES factuur (id),
organisatie_id int NOT NULL REFERENCES organisatie (id),
header VARCHAR NOT NULL,
body VARCHAR NOT NULL,
footer VARCHAR NOT NULL
);

CREATE TABLE opmerking (
id int NOT NULL PRIMARY KEY,
opmerking TEXT NOT NULL
);

/**
* Product table 
*/
CREATE TABLE product (

	id SERIAL NOT NULL PRIMARY KEY,
	productnummer int NOT NULL,
	naam VARCHAR NOT NULL,
	jaar int NOT NULL,
	prijs decimal NOT NULL,
	type VARCHAR,
	land_id int NOT NULL REFERENCES land (id),
	rang int  	
);

/**
* Order table
*/
CREATE TABLE tbl_order (

	id SERIAL NOT NULL PRIMARY KEY,
	factur_id int NOT NULL REFERENCES factuur (id),
	aantal int NOT NULL,
	product_id int NOT NULL REFERENCES product (id)
);

/**
* Notitie to debiteur koppel tabel
*/
CREATE TABLE notitie_to_debiteur (
	id SERIAL NOT NULL PRIMARY KEY,
	notitie_id int NOT NULL REFERENCES notitie (id),
	debiteur_id int NOT NULL REFERENCES debiteur (id)
);

/**
* Notitie to factuur koppel tabel
*/
CREATE TABLE notitie_to_factuur (
	id SERIAL NOT NULL PRIMARY KEY,
	notitie_id int NOT NULL REFERENCES notitie (id),
	factuur_id int NOT NULL REFERENCES factuur (id)
);
/**
* Opmerking to debiteur koppel tabel
*/
CREATE TABLE opmerking_to_debiteur (
	id SERIAL NOT NULL PRIMARY KEY,
	opmerking_id int NOT NULL REFERENCES opmerking (id),
	debiteur_id int NOT NULL REFERENCES debiteur (id)
);

/**
* Opmerking to debiteur koppel tabel
*/
CREATE TABLE opmerking_to_factuur (
	id SERIAL NOT NULL PRIMARY KEY,
	opmerking_id int NOT NULL REFERENCES opmerking (id),
	factuur_id int NOT NULL REFERENCES factuur (id)
);