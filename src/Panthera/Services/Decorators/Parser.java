package Panthera.Services.Decorators;

import Panthera.Models.Model;

/**
 * Decorator component used for parsing replace codes in strings.
 *
 */
public abstract class Parser extends Model {

    public abstract String parse(String text, Model data);

}
