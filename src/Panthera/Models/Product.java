package Panthera.Models;

import javafx.beans.property.*;

/**
 * Model representing a product.
 *
 * @author Daan Rosbergen
 */
public class Product extends Model {

    private SimpleIntegerProperty id;
    private SimpleIntegerProperty productnummer;
    private SimpleStringProperty naam;
    private SimpleIntegerProperty jaar;
    private SimpleDoubleProperty prijs;
    private SimpleStringProperty type;
    private SimpleObjectProperty<Land> land;
    private SimpleBooleanProperty active;
    private SimpleStringProperty aantal;

    public Product(int id, int productnummer, String naam, int jaar, double prijs, String type, Land land) {
        this.id = new SimpleIntegerProperty(id);
        this.productnummer = new SimpleIntegerProperty(productnummer);
        this.naam = new SimpleStringProperty(naam);
        this.jaar = new SimpleIntegerProperty(jaar);
        this.prijs = new SimpleDoubleProperty(prijs);
        this.type = new SimpleStringProperty(type);
        this.land = new SimpleObjectProperty<>(land);
        this.active = new SimpleBooleanProperty(false);
        this.aantal = new SimpleStringProperty("leeg");
    }

    public Product(int productnummer, String naam, int jaar, double prijs, String type, Land land) {
        this.id = new SimpleIntegerProperty();
        this.productnummer = new SimpleIntegerProperty(productnummer);
        this.naam = new SimpleStringProperty(naam);
        this.jaar = new SimpleIntegerProperty(jaar);
        this.prijs = new SimpleDoubleProperty(prijs);
        this.type = new SimpleStringProperty(type);
        this.land = new SimpleObjectProperty<>(land);
        this.active = new SimpleBooleanProperty(false);
        this.aantal = new SimpleStringProperty("leeg");
    }

    public Product() {
        this.id = new SimpleIntegerProperty();
        this.productnummer = new SimpleIntegerProperty();
        this.naam = new SimpleStringProperty();
        this.jaar = new SimpleIntegerProperty();
        this.prijs = new SimpleDoubleProperty();
        this.type = new SimpleStringProperty();
        this.land = new SimpleObjectProperty<>();
        this.active = new SimpleBooleanProperty();
        this.aantal = new SimpleStringProperty();
    }

    /**
     * Retrieve productnumber.
     *
     * @author Daan Rosbergen
     * @return
     */
    public int getProductnummer() {
        return productnummer.get();
    }

    /**
     * Productnumber property used to observe its value.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleIntegerProperty productnummerProperty() {
        return productnummer;
    }

    /**
     * Set productnumber.
     *
     * @author Daan Rosbergen
     * @param productnummer
     */
    public void setProductnummer(int productnummer) {
        this.productnummer.set(productnummer);
    }

    /**
     * Get name of product.
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getNaam() {
        return naam.get();
    }

    /**
     * Get name property to observe its value.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty naamProperty() {
        return naam;
    }

    /**
     * Set productnaam.
     *
     * @author Daan Rosbergen
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    /**
     * Get product year (wine year).
     *
     * @author Daan Rosbergen
     * @return
     */
    public int getJaar() {
        return jaar.get();
    }

    /**
     * Product year property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleIntegerProperty jaarProperty() {
        return jaar;
    }


    /**
     * Set product year (wine year).
     *
     * @author Daan Rosbergen
     * @param jaar
     */
    public void setJaar(int jaar) {
        this.jaar.set(jaar);
    }

    /**
     * Get product price.
     *
     * @author Daan Rosbergen
     * @return
     */
    public double getPrijs() {
        return prijs.get();
    }

    /**
     * Product price property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleDoubleProperty prijsProperty() {
        return prijs;
    }

    /**
     * Set product price.
     *
     * @author Daan Rosbergen
     * @param prijs
     */
    public void setPrijs(double prijs) {
        this.prijs.set(prijs);
    }

    /**
     * Get product type (White, Red or Rose wine).
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getType() {
        return type.get();
    }

    /**
     * Product type property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty typeProperty() {
        return type;
    }

    /**
     * Set product type.
     * @author Daan Rosbergen
     * @param type
     */
    public void setType(String type) {
        this.type.set(type);
    }

    /**
     * Get product id.
     *
     * @author Daan Rosbergen
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Id property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Set product id.
     *
     * @author Daan Rosbergen
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Return land model of product.
     *
     * @author Daan Rosbergen
     * @return
     */
    public Land getLand() {
        return land.get();
    }

    /**
     * Land property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleObjectProperty<Land> landProperty() {
        return land;
    }

    /**
     * Set land model to product.
     *
     * @author Daan Rosbergen
     * @param land
     */
    public void setLand(Land land) {
        this.land.set(land);
    }

    /**
     * @author Daan Rosbergen
     * @return
     */
    @Override public String toString() {
        return "Product: \n"
            + "Nummer: " + productnummer + "\n"
            + "Naam: " + naam + "\n"
            + "Jaar: " + jaar + "\n"
            + "Prijs: " + prijs + "\n"
            + "Type: " + type;
    }

    /**
     * Used to determine if product has an id.
     *
     * @author Daan Rosbergen
     * @return
     */
    public boolean hasId() {
        return (id.get() != 0);
    }

    /**
     * Used to determine if product is active in a list.
     *
     * @author Daan Rosbergen
     * @return
     */
    public boolean isActive() {
        return active.get();
    }

    /**
     * Active boolean property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    /**
     * Set product active.
     *
     * @author Daan Rosbergen
     * @param active
     */
    public void setActive(boolean active) {
        this.active.set(active);
    }

    /**
     * Get product amount.
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getAantal() {
        return aantal.get();
    }

    /**
     * Product aantal property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty aantalProperty() {
        return aantal;
    }

    /**
     * Set product amount.
     *
     * @author Daan Rosbergen
     * @param aantal
     */
    public void setAantal(String aantal) {
        this.aantal.set(aantal);
    }
}
