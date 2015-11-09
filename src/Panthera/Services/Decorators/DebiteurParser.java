package Panthera.Services.Decorators;

import Panthera.Models.Debiteur;
import Panthera.Models.Model;

/**
 * Parser for debiteur models, used for replace codes in for example emails or templates. This
 * parser uses the decorator pattern.
 *
 * Replace codes:
 * - `{LID}` Example: Daan Rosbergen or Brandon van Wijk.
 *
 * @author Daan Rosbergen
 */
public class DebiteurParser extends Parser {

    private Model parser;
    private Debiteur debiteur;

    public DebiteurParser(Model parser) {
        this.parser = parser;
    }

    /**
     * Parse replace codes for debiteur.
     *  - {LID}: Example: Daan Rosbergen or Brandon van Wijk.
     *
     * @param text
     * @param data
     * @return
     */
    @Override public String parse(String text, Model data) {
        this.debiteur = (Debiteur)data;
        return parser.parse(text, parser).replace("{LID}", debiteur.getAanhef() + " " +
            debiteur.getVoornaam() + " " + parseTussenvoegsel() + debiteur.getNaam());
    }

    /**
     * Checks if tussenvoegsel is null and parses it accordingly.
     *
     * @return
     */
    private String parseTussenvoegsel() {
        String tussenvoegsel = "";
        if (debiteur.getTussenvoegsel() != null) {
            tussenvoegsel += debiteur.getTussenvoegsel() + " ";
        }
        return tussenvoegsel;
    }
}
