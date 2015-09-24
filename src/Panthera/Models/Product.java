package Panthera.Models;

public class Product {

    private int id;
    private int productnummer;
    private String naam;
    private int jaar;
    private double prijs;
    private String type;

    public Product(int id, int productnummer, String naam, int jaar, double prijs, String type) {
        this.id = id;
        this.productnummer = productnummer;
        this.naam = naam;
        this.jaar = jaar;
        this.prijs = prijs;
        this.type = type;
    }

    public Product() {

    }

    public int getId() {
        return id;
    }
//
//    public void setId() {
//        this.id = id;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductnummer() {
        return productnummer;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override public String toString() {
        return "Product: \n"
            + "Nummer: " + productnummer + "\n"
            + "Naam: " + naam + "\n"
            + "Jaar: " + jaar + "\n"
            + "Prijs: " + prijs + "\n"
            + "Type: " + type;
    }
}
