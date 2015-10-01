package Panthera.Services.Decorators;

import Panthera.Models.Debiteur;
import Panthera.Models.Model;

public class DebiteurParser extends Parser {

    private Model parser;
    private Debiteur debiteur;

    public DebiteurParser(Model parser) {
        this.parser = parser;
    }

    @Override public String parse(String text, Model data) {
        this.debiteur = (Debiteur)data;
        return parser.parse(text, parser).replace("{LID}", debiteur.getAanhef() + " " + debiteur.getVoornaam() + " " + parseTussenvoegsel() + debiteur.getNaam());
    }

    private String parseTussenvoegsel() {
        String tussenvoegsel = "";
        if (debiteur.getTussenvoegsel() != null) {
            tussenvoegsel += debiteur.getTussenvoegsel() + " ";
        }
        return tussenvoegsel;
    }
}
