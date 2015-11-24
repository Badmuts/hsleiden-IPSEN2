package Panthera.Models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author Brandon van Wijk
 * @author Daan Rosbergen
 * Dit object reprensenteert een factuurregel. Een factuurregel bestaat uit
 * een product en een aantal.
 *
 * Deze klasse berekend zelf het subtotaal van de factuurregel.
 *
 * Per factuurregel kan het BTW percentage worden ingesteld.
 */
public class Factuurregel extends Model {

    /**
     * attributen
     */
    private SimpleIntegerProperty aantal;
    private SimpleObjectProperty<Product> product;
    private SimpleDoubleProperty subtotaal = new SimpleDoubleProperty(0.0);
    private int btwPercentage = 121;
    private double totaalprijs;
    private SimpleDoubleProperty btw;

    public Factuurregel() {
        super();
    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze constructor instantiert de attributen en berekent het
     * subtotaal van de factuurregel
     * @param aantal
     * @param product
     */
    public Factuurregel(int aantal, Product product) {
        super();
        this.aantal = new SimpleIntegerProperty(aantal);
        this.product = new SimpleObjectProperty<>(product);
        this.btw = new SimpleDoubleProperty();
        berekenSubtotaal();
        berekenBTW();
    }

    /**
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode berekend het subtotaal aan de hand van
     * De producten in de factuuregel en de bijbehordende aantallen per product
     * Ook wordt de btw meegerekend.
     */
    public void berekenSubtotaal() {
        this.totaalprijs = getAantal() * getProduct().getPrijs();
        setSubtotaal(totaalprijs / getBtw() * 100);
    }

    /**
     * deze methode berekent het btw bedrag van de producten om de factuur
     */
    public void berekenBTW() {
        setBtw((this.totaalprijs / getBtwPercentage()) * 21);
    }

    //get methodes
    public double getPrijs() {
        return getProduct().getPrijs();
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
    public Product getProduct() {
        return product.get();
    }
    public double getSubtotaal() {
        return subtotaal.get();
    }
    public double getBtw() {
        return btw.get();
    }
    //set methodes
    public void setBtw(double btw) {
        this.btw.set(btw);
    }
    public void setSubtotaal(double subtotaal) {
        this.subtotaal.set(subtotaal);
    }
    public void setProduct(Product product) {
        this.product.set(product);
    }
    public void setAantal(int aantal) {
        this.aantal.set(aantal);
    }
    //properties
    public SimpleIntegerProperty aantalProperty() {
        return aantal;
    }
    public SimpleObjectProperty<Product> productProperty() {
        return product;
    }
    public SimpleDoubleProperty subtotaalProperty() {
        return subtotaal;
    }
    public SimpleDoubleProperty btwProperty() {
        return btw;
    }


}
