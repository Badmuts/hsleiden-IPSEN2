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
     * @return
     */
    public int getProductnummer() {
        return productnummer.get();
    }

    /**
     * Productnumber property used to observe its value.
     *
     * @return
     */
    public SimpleIntegerProperty productnummerProperty() {
        return productnummer;
    }

    /**
     * Set productnumber.
     *
     * @param productnummer
     */
    public void setProductnummer(int productnummer) {
        this.productnummer.set(productnummer);
    }

    /**
     * Get name of product.
     * @return
     */
    public String getNaam() {
        return naam.get();
    }

    /**
     * Get name property to observe its value.
     *
     * @return
     */
    public SimpleStringProperty naamProperty() {
        return naam;
    }

    /**
     * Set productnaam.
     *
     * @param naam
     */
    public void setNaam(String naam) {
        this.naam.set(naam);
    }

    /**
     * Get product year (wine year).
     *
     * @return
     */
    public int getJaar() {
        return jaar.get();
    }

    /**
     * Product year property.
     *
     * @return
     */
    public SimpleIntegerProperty jaarProperty() {
        return jaar;
    }


    /**
     * Set product year (wine year).
     *
     * @param jaar
     */
    public void setJaar(int jaar) {
        this.jaar.set(jaar);
    }

    /**
     * Get product price.
     *
     * @return
     */
    public double getPrijs() {
        return prijs.get();
    }

    /**
     * Product price property.
     *
     * @return
     */
    public SimpleDoubleProperty prijsProperty() {
        return prijs;
    }

    /**
     * Set product price.
     *
     * @param prijs
     */
    public void setPrijs(double prijs) {
        this.prijs.set(prijs);
    }

    /**
     * Get product type (White, Red or Rose wine).
     *
     * @return
     */
    public String getType() {
        return type.get();
    }

    /**
     * Product type property.
     *
     * @return
     */
    public SimpleStringProperty typeProperty() {
        return type;
    }

    /**
     * Set product type.
     *
     * @param type
     */
    public void setType(String type) {
        this.type.set(type);
    }

    /**
     * Get product id.
     *
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Id property.
     *
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Set product id.
     *
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Return land model of product.
     *
     * @return
     */
    public Land getLand() {
        return land.get();
    }

    /**
     * Land property.
     * @return
     */
    public SimpleObjectProperty<Land> landProperty() {
        return land;
    }

    /**
     * Set land model to product.
     *
     * @param land
     */
    public void setLand(Land land) {
        this.land.set(land);
    }

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
     * @return
     */
    public boolean hasId() {
        return (id.get() != 0);
    }

    /**
     * Used to determine if product is active in a list.
     *
     * @return
     */
    public boolean isActive() {
        return active.get();
    }

    /**
     * Active boolean property.
     *
     * @return
     */
    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    /**
     * Set product active.
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active.set(active);
    }

    /**
     * Get product amount.
     *
     * @return
     */
    public String getAantal() {
        return aantal.get();
    }

    /**
     * Product aantal property.
     *
     * @return
     */
    public SimpleStringProperty aantalProperty() {
        return aantal;
    }

    /**
     * Set product amount.
     *
     * @param aantal
     */
    public void setAantal(String aantal) {
        this.aantal.set(aantal);
    }
}
