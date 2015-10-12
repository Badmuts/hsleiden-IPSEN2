# IPSEN2
Project Panthera.Panthera, een ordersysteem voor het wijnfestijn van de Lions Club. Panthera.Panthera is het Latijnse woord voor leeuw.

## Setup database
### Download JDBC driver
Om connectie met je database te maken moet je de [Postgres driver downloaden](https://jdbc.postgresql.org/download.html) en toevoegen aan je project. Met InteliJ kun je dit doen door op naar File -> Project Structure -> Global Libraries te gaan en daar de jar file toe te voegen.

### Import database
In het mapje `DB` staat een sql bestand: `database.sql`. Importeer deze in een nieuwe database.

### Connectie gegevens aanpassen
Configuratie met de database staat in de `DatabaseService.java` class. Hierin moet je je login gegevens aanpassen. Vergeet ook niet de databasenaam aan te passsen. Gegevens staan nu in de `getConnection()` method. 

## Mailservice
Het sturen van een mail is gelukkig erg makkelijk door de `MailService` class. De `MailService` class heeft een simpele methode `send` die een `Email` model verwacht. Ik heb hier wat voorbeeldcode.

Maak een `MailService` object:
```java
MailService mailService = new MailService();
// of
MailService mailService = new MailService(new SendGrid());
```
Maak een nieuw `Email` object en voeg gegevens toe
```java
Email email = new Email();
email.setTo("d.rosbergen@gmail.com");
email.setFrom("daan@daanrosbergen.nl");
email.setSubject("Java mail test");
email.setText("Hoi dit is Daan!");
email.addAttachment("daan.test", "daantestattachment.txt");
```
Verstuur je `Email` via de `MailService`.
```java
mailService.send(email);
```
## Structuur
Tijdens dit project willen wij de volgende applicatie structuur gaan gebruiken: MVC, DAO en Services.

![Applicatie structuur](https://www.dropbox.com/s/f3mrd5j1sl9qy3s/DAOModel.JPG?dl=1)

## Usecase diagram  
![Usecase diagram](https://photos-1.dropbox.com/t/2/AABCAudwgquWRLV6g3YeCCwPYnaSmgeI6Qo-ixNv5brilQ/12/48853717/png/32x32/1/_/1/2/Use%20Case%20diagram.png/EOjciuADGOoEIAEgBygH/CEP3V1-43diNiUGqvM6LWnEnuhHZ18BvWkXMsltkQU0?size=1024x768&size_mode=2)

## ERD
![ERD](https://www.dropbox.com/s/9865z99tks0p5jv/erd-v0.3.png?dl=1)

## Domeinmodel
![Domeinmodel](https://www.dropbox.com/s/rz546a32v9jvamb/DomeinModel.jpg?dl=1)

## Applicatiemodel
![Applicatiemodel](https://www.dropbox.com/s/rf6q8kofec5ujpj/ApplicatieModel1.2.jpg?dl=1)

