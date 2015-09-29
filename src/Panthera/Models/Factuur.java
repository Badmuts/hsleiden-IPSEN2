package Panthera.Models;

import Panthera.DAO.FactuurDAO;
import javafx.beans.property.*;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class Factuur {
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty factuurnummer;
    private SimpleObjectProperty<Date> factuurdatum;
    private SimpleObjectProperty<Date> vervaldatum;
    private SimpleStringProperty status;
    private SimpleBooleanProperty checked;
    private SimpleObjectProperty<Bestellijst> bestellijst;


    //private Debiteur debiteur;
    //private OrderRegel orderRegel;
    //private String opmerking;
    //private String notitie;
    //private Organisatie organisatie;




    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, Bestellijst bestellijst) {
        this.id = new SimpleIntegerProperty(id);
        this.factuurnummer = new SimpleIntegerProperty(factuurnummer);
        this.factuurdatum = new SimpleObjectProperty<>(factuurdatum);
        this.vervaldatum = new SimpleObjectProperty<>(vervaldatum);
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        this.bestellijst = new SimpleObjectProperty<>(bestellijst);
        //this.debiteur = debiteur;
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
        this.bestellijst = new SimpleObjectProperty<>();
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

    public Bestellijst getBestellijst() {
        return bestellijst.get();
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

    public void setVervaldatum(Date vervaldatum) {
        this.vervaldatum.set(vervaldatum);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
    }

    public void setBestellijst(Bestellijst bestellijst) {
        this.bestellijst.set(bestellijst);
    }

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

    public SimpleObjectProperty bestellijstProperty() {
        return this.bestellijst;
    }





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

}


