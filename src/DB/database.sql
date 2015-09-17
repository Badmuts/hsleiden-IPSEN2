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

CREATE TABLE notitie (
id INTEGER(11) NOT NULL PRIMARY KEY,
titel VARCHAR(255) NOT NULL,
notitie TEXT NOT NULL
)

CREATE TABLE mail (
id INTEGER(11) NOT NULL PRIMARY KEY,
debiteur_id INTEGER(11) NOT NULL FOREIGN KEY (debiteur_id) REFERENCES debiteur(id),
factuur_id INTEGER(11) NOT NULL FOREIGN KEY (factuur_id) REFERENCES factuur(id),
organisatie_id INTEGER(11) NOT NULL FOREIGN KEY (organisatie_id) REFERENCES organisatie(id),
header VARCHAR(255) NOT NULL,
body VARCHAR(255) NOT NULL,
footer VARCHAR(255) NOT NULL
)

CREATE TABLE opmerking (
id INTEGER(255) NOT NULL PRIMARY KEY,
opmerking TEXT NOT NULL
)