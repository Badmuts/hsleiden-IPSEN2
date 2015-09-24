package Panthera.Models;

import Panthera.DAO.FactuurDAO;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class Factuur {
    //private int id;
    private Date factuurdatum;
    private Date vervaldatum;
    //private Debiteur debiteur;
    //private OrderRegel orderRegel;
    //private String opmerking;
    //private String notitie;
    //private Organisatie organisatie;
    private String status;
    private int factuurnummer;


    public Factuur(int factuurnummer, Date factuurdatum, Date vervaldatum, String status) {
        //this.id = id;
        this.factuurnummer = factuurnummer;
        this.factuurdatum = factuurdatum;
        this.vervaldatum = vervaldatum;
        //this.debiteur = debiteur;
        //this.orderRegel = orderRegel;
       // this.opmerking = opmerking;
       // this.notitie = notitie;
        this.status = status;


    }

    public Factuur() {

    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public int getFactuurnummer() {
        return this.factuurnummer;
    }

    public void setFactuurnummer(int factuurnummer) {
        this.factuurnummer = factuurnummer;
    }


    public void setFactuurdatum(Date factuurdatum) {
        this.factuurdatum = factuurdatum;
    }

    public void setVervaldatum(Date vervaldatum) {
        this.vervaldatum = vervaldatum;
    }

    public void setStatus(String status) {
        this.status = status;
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
            return "Factuur: " + this.factuurdatum + " " + this.vervaldatum + " " + this.status;
        }

}


