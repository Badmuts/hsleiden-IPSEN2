package Panthera.Services.Decorators;

import Panthera.Models.Factuur;
import Panthera.Models.Model;

/**
 * Parser for Factuur models, used for replace codes in for example emails or templates. This
 * parser uses the decorator pattern.
 *
 * Replace codes:
 * - `{FACTUURNUMMER}` Displays the invoicenumber.
 *
 * @author Daan Rosbergen
 */
public class FactuurParser extends Parser {

    private Parser parser;

    public FactuurParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * Parse replace codes for factuur.
     *  - `{FACTUURNUMMER}`: Displays invoicenumber.
     *
     * @param text
     * @param data
     * @return
     */
    @Override public String parse(String text, Model data) {
        Factuur factuur = (Factuur)data;
        return parser.parse(text, parser).replace("{FACTUURNUMMER}", String.valueOf(factuur.getFactuurnummer()));
    }
}
