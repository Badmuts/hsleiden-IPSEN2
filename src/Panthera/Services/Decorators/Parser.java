package Panthera.Services.Decorators;

import Panthera.Models.Model;

public abstract class Parser extends Model {

    public abstract String parse(String text, Model data);

}
