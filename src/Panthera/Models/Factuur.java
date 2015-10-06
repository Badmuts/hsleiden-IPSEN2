package Panthera.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Brandon on 22-Sep-15.
 */

public class Factuur extends Model {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty factuurnummer;
    private SimpleObjectProperty<Date> factuurdatum;
    private SimpleObjectProperty<Date> vervaldatum;
    private SimpleStringProperty status;
    private SimpleBooleanProperty checked;
    private SimpleObjectProperty<Debiteur> debiteur;
    private SimpleObjectProperty<ArrayList<Factuurregel>> factuurregels;
    //private OrderRegel orderRegel;
    //private String opmerking;
    //private String notitie;
    //private Organisatie organisatie;


    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, Debiteur debiteur, ArrayList<Factuurregel> factuurregels) {

        this.id = new SimpleIntegerProperty(id);
        this.factuurnummer = new SimpleIntegerProperty(factuurnummer);
        this.factuurdatum = new SimpleObjectProperty<>(factuurdatum);
        this.vervaldatum = new SimpleObjectProperty<>(vervaldatum);
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        this.debiteur = new SimpleObjectProperty<>(debiteur);
        this.factuurregels = new SimpleObjectProperty<>(factuurregels);
        //this.orderRegel = orderRegel;
       // this.opmerking = opmerking;
       // this.notitie = notitie;
    }

    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, Debiteur debiteur) {

        this.id = new SimpleIntegerProperty(id);
        this.factuurnummer = new SimpleIntegerProperty(factuurnummer);
        this.factuurdatum = new SimpleObjectProperty<>(factuurdatum);
        this.vervaldatum = new SimpleObjectProperty<>(vervaldatum);
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        this.debiteur = new SimpleObjectProperty<>(debiteur);
        this.factuurregels = new SimpleObjectProperty<>(new ArrayList<>());
        //this.orderRegel = orderRegel;
       // this.opmerking = opmerking;
       // this.notitie = notitie;
    }

    public Factuur() {
        this.id = new SimpleIntegerProperty();
        this.factuurnummer = new SimpleIntegerProperty();
        this.factuurdatum = new SimpleObjectProperty<>();
        this.vervaldatum = new SimpleObjectProperty<>();
        this.status = new SimpleStringProperty();
        this.checked = new SimpleBooleanProperty();
        this.debiteur = new SimpleObjectProperty<>();
        this.factuurregels = new SimpleObjectProperty<>(new ArrayList<Factuurregel>());
    }

    //Getters
    public int getId() {
        return id.get();
    }

    public int getFactuurnummer() {
        return factuurnummer.get();
    }

    public Date getFactuurdatum() {
        return factuurdatum.get();
    }

    public Date getVervaldatum() {
        return vervaldatum.get();
    }

    public String getStatus() {
        return status.get();
    }

    public Boolean isChecked() {
        return this.checked.get();
    }

    public Debiteur getDebiteur() {
        return debiteur.get();
    }


    //Setters
    public void setId(int id) {
        this.id.set(id);
    }


    public void setFactuurnummer(int factuurnummer) {
        this.factuurnummer.set(factuurnummer);
    }

    public void setFactuurdatum(Date factuurdatum) {
        this.factuurdatum.set(factuurdatum);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }


    public void setVervaldatum(Date vervaldatum) {
        this.vervaldatum.set(vervaldatum);
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public void setDebiteur(Debiteur debiteur) { this.debiteur.set(debiteur);}


    //Properties
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleIntegerProperty factuurnummerProperty() {
        return factuurnummer;
    }

    public SimpleObjectProperty factuurdatumProperty() {
        return factuurdatum;
    }

    public SimpleObjectProperty vervaldatumProperty() {
        return vervaldatum;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public SimpleObjectProperty debiteurProperty() { return this.debiteur; }






//    public OrderRegel getOrderRegel() {
//        return orderRegel;
//    }
//
//    public Organisatie getOrganisatie() {
//        return organisatie;
//    }
//
//    public String getOpmerking() {
//        return opmerking;
//    }
//
//    public String getNotitie() {
//        return notitie;
//    }
//
//    public void setOpmerking(String opmerking) {
//        this.opmerking = opmerking;
//    }
//
//    public void setNotitie(String notitie) {
//        this.notitie = notitie;
//    }

        public String toString() {
            return "Factuur: " + this.factuurnummer + " " + this.factuurdatum + " " + this.vervaldatum + " " + this.status;
        }

    public ArrayList<Factuurregel> getFactuurregels() {
        return factuurregels.get();
    }

    public SimpleObjectProperty<ArrayList<Factuurregel>> factuurregelsProperty() {
        return factuurregels;
    }

    public void setFactuurregels(ArrayList<Factuurregel> factuurregels) {
        this.factuurregels.set(factuurregels);
    }

    public void addFactuurregel(Factuurregel factuurregel) {
        this.factuurregels.get().add(factuurregel);
    }
}


