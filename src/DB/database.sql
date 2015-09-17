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
	factuurdatum DATE NOT NULL,
	vervaldatum DATE NOT NULL,
	status VARCHAR NOT NULL

);