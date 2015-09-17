CREATE TABLE factuur (

)

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