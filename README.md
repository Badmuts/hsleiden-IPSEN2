# IPSEN2
Project Panthera.Panthera, een ordersysteem voor het wijnfestijn van de Lions Club. Panthera.Panthera is het Latijnse woord voor leeuw.

## Setup database
### Download JDBC driver
Om connectie met je database te maken moet je de [Postgres driver downloaden](https://jdbc.postgresql.org/download.html) en toevoegen aan je project. Met InteliJ kun je dit doen door op naar File -> Project Structure -> Global Libraries te gaan en daar de jar file toe te voegen.

### Import database
In het mapje `DB` staat een sql bestand: `database.sql`. Importeer deze in een nieuwe database.

### Connectie gegevens aanpassen
Configuratie met de database staat in de `DatabaseService.java` class. Hierin moet je je login gegevens aanpassen. Vergeet ook niet de databasenaam aan te passsen. Gegevens staan nu in de `getConnection()` method. 

## Structuur
Tijdens dit project willen wij de volgende applicatie structuur gaan gebruiken: MVC, DAO en Services.

![Applicatie structuur](https://www.dropbox.com/s/f3mrd5j1sl9qy3s/DAOModel.JPG?dl=1)

## Usecase diagram  
![Usecase diagram](https://www.dropbox.com/home/IIPSEN2/Functioneel%20ontwerp?preview=Use+Case+diagram.png)

## ERD
![ERD](https://www.dropbox.com/s/9865z99tks0p5jv/erd-v0.3.png?dl=1)

## Domeinmodel
![Domeinmodel](https://www.dropbox.com/s/rz546a32v9jvamb/DomeinModel.jpg?dl=1)

## Applicatiemodel
![Applicatiemodel](https://www.dropbox.com/s/rf6q8kofec5ujpj/ApplicatieModel1.2.jpg?dl=1)

