package Panthera.Services.Decorators;

import Panthera.Models.Debiteur;
import Panthera.Models.Model;

public class DebiteurParser extends Parser {

    private Model parser;

    public DebiteurParser(Model parser) {
        this.parser = parser;
    }

    @Override public String parse(String text, Model data) {
        Debiteur debiteur = (Debiteur)data;
        return parser.parse(text, parser).replace("{LID}", debiteur.getAanhef() + " " + debiteur.getVoornaam() + " " + debiteur.getTussenvoegsel() + " " + debiteur.getNaam());
    }
}
