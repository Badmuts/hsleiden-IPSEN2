package Panthera.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Daan on 27-Sep-15.
 */
public class Land extends Model {

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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Land land = (Land) o;

        if (id != null ? !id.equals(land.id) : land.id != null)
            return false;
        return !(naam != null ? !naam.equals(land.naam) : land.naam != null);

    }

    @Override public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }
}
