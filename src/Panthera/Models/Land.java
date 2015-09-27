package Panthera.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Daan on 27-Sep-15.
 */
public class Land {

    private SimpleIntegerProperty id;
    private SimpleStringProperty naam;

    public Land() {
        this.id = new SimpleIntegerProperty();
        this.naam = new SimpleStringProperty();
    }

    public Land(int id, String naam) {
        this.id = new SimpleIntegerProperty(id);
        this.naam = new SimpleStringProperty(naam);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNaam() {
        return naam.get();
    }

    public SimpleStringProperty naamProperty() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    @Override
    public String toString() {
        return naam.get();
    }
}
