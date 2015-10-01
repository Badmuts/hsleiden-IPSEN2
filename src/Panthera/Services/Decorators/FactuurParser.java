package Panthera.Services.Decorators;

import Panthera.Models.Factuur;
import Panthera.Models.Model;

public class FactuurParser extends Parser {

    private Parser parser;

    public FactuurParser(Parser parser) {
        this.parser = parser;
    }

    @Override public String parse(String text, Model data) {
        Factuur factuur = (Factuur)data;
        return parser.parse(text, parser).replace("{FACTUURNUMMER}", String.valueOf(factuur.getFactuurnummer()));
    }
}
