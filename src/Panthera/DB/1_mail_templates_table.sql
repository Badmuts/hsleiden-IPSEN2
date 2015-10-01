CREATE TABLE mail_templates(
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  active BOOLEAN NOT NULL
);


INSERT INTO mail_templates(name, content, active) VALUES ('Dankwoord', 'Beste {KLANT}, Bedankt voor je aanwezigheid tijdens ons wijnfestijn. Met vriendelijke groet,
Daan Rosbergen', true);

INSERT INTO mail_templates(name, content, active) VALUES ('Dankwoord lid', 'Beste {LID},

Bedankt dat je ons dit jaar weer hebt vergezeld.

Met vriendelijke groet,
Lions', true);
