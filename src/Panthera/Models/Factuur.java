package Panthera.Models;

import Panthera.DAO.FactuurDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class Factuur {
    private int id;
    private int factuurnummer;
    private Date factuurdatum;
    private Date vervaldatum;
    private SimpleStringProperty status;
    private SimpleBooleanProperty checked;


    //private Debiteur debiteur;
    //private OrderRegel orderRegel;
    //private String opmerking;
    //private String notitie;
    //private Organisatie organisatie;




    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status) {
        this.id = id;
        this.factuurnummer = factuurnummer;
        this.factuurdatum = factuurdatum;
        this.vervaldatum = vervaldatum;
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        //this.debiteur = debiteur;
        //this.orderRegel = orderRegel;
       // this.opmerking = opmerking;
       // this.notitie = notitie;
    }

    public Factuur() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFactuurnummer() {
        return factuurnummer;
    }

    public void setFactuurnummer(int factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    public Date getFactuurdatum() {
        return factuurdatum;
    }
    public void setFactuurdatum(Date factuurdatum) {
        this.factuurdatum = factuurdatum;
    }

    public Date getVervaldatum() {
        return vervaldatum;
    }
    public void setVervaldatum(Date vervaldatum) {
        this.vervaldatum = vervaldatum;
    }

    public String getStatus() {
        return status.get();
    }
    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return this.status;
    }

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public Boolean isChecked() {
        return this.checked.get();
    }

    public void setChecked(Boolean checked) {
        this.checked.set(checked);
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


