package Panthera.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model representing a country.
 *
 * @author Daan Rosbergen
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

    /**
     * Get the id of the corresponding land in the DB.
     *
     * @author Daan Rosbergen
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Id property used to observe the id value.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Set id.
     *
     * @author Daan Rosbergen
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Get name of land.
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getNaam() {
        return naam.get();
    }

    /**
     * Name property used to observe its value.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty naamProperty() {
        return naam;
    }

    /**
     * Set name.
     *
     * @author Daan Rosbergen
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    /**
     * @author Daan Rosbergen
     * @return
     */
    @Override
    public String toString() {
        return naam.get();
    }

    /**
     * @author Daan Rosbergen
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Land land = (Land) o;

        if (id != null ? !id.equals(land.id) : land.id != null) return false;
        return !(naam != null ? !naam.equals(land.naam) : land.naam != null);

    }

    /**
     * @author Daan Rosbergen
     * @return
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }
}
