package Panthera.Models;

/**
 * Super class of all Models. Used for decorator (hack) pattern.
 *
 * @author Daan Rosbergen
 */
public class Model {

    /**
     * Method used by decorators.
     *
     * @author Daan Rosbergen
     * @param text  String to parse.
     * @param data
     * @return      Parsed string.
     */
    public String parse(String text, Model data) {
        return text;
    }

}
