package Panthera.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Dit object reprensenteert een factuurregel. Een factuurregel bestaat uit
 * een product en een aantal.
 *
 * Deze klasse berekend zelf het subtotaal van de factuurregel.
 *
 * Per factuurregel kan het BTW percentage worden ingesteld.
 */
public class Factuurregel extends Model {

    private SimpleIntegerProperty aantal;
    private SimpleObjectProperty<Product> product;
    private SimpleDoubleProperty subtotaal = new SimpleDoubleProperty(0.0);
    private int btwPercentage = 121;
    private double totaalprijs;
    private SimpleDoubleProperty btw;

    public Factuurregel() {
        super();
    }

    public Factuurregel(int aantal, Product product) {
        super();
        this.aantal = new SimpleIntegerProperty(aantal);
        this.product = new SimpleObjectProperty<>(product);
        this.btw = new SimpleDoubleProperty();
        berekenSubtotaal();
        berekenBTW();
    }

    public void berekenSubtotaal() {
        this.totaalprijs = getAantal() * getProduct().getPrijs();
        setSubtotaal(totaalprijs / getBtw() * 100);
    }

    public double getPrijs() {
        return getProduct().getPrijs();
    }
    public void berekenBTW() {
        setBtw((this.totaalprijs / getBtwPercentage()) * 21);
    }
    public double getTotaal() {
        return this.totaalprijs;
    }
    public int getBtwPercentage() {
        return this.btwPercentage;
    }
    public int getAantal() {
        return aantal.get();
    }

    public SimpleIntegerProperty aantalProperty() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal.set(aantal);
    }

    public Product getProduct() {
        return product.get();
    }

    public SimpleObjectProperty<Product> productProperty() {
        return product;
    }

    public void setProduct(Product product) {
        this.product.set(product);
    }

    public double getSubtotaal() {
        return subtotaal.get();
    }

    public SimpleDoubleProperty subtotaalProperty() {
        return subtotaal;
    }

    public void setSubtotaal(double subtotaal) {
        this.subtotaal.set(subtotaal);
    }

    public double getBtw() {
        return btw.get();
    }

    public SimpleDoubleProperty btwProperty() {
        return btw;
    }

    public void setBtw(double btw) {
        this.btw.set(btw);
    }
}
