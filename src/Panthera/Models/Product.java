package Panthera.Models;

import javafx.beans.property.*;


public class Product {

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

    public int getProductnummer() {
        return productnummer.get();
    }

    public SimpleIntegerProperty productnummerProperty() {
        return productnummer;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer.set(productnummer);
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

    public int getJaar() {
        return jaar.get();
    }

    public SimpleIntegerProperty jaarProperty() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar.set(jaar);
    }

    public double getPrijs() {
        return prijs.get();
    }

    public SimpleDoubleProperty prijsProperty() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs.set(prijs);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
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

    public Land getLand() {
        return land.get();
    }

    public SimpleObjectProperty<Land> landProperty() {
        return land;
    }

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

    public boolean hasId() {
        return (id.get() != 0);
    }

    public boolean isActive() {
        return active.get();
    }

    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public String getAantal() {
        return aantal.get();
    }

    public SimpleStringProperty aantalProperty() {
        return aantal;
    }

    public void setAantal(String aantal) {
        this.aantal.set(aantal);
    }
}
