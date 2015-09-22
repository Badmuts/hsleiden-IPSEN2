package Panthera.Models;

import Panthera.DAO.FactuurDAO;

import java.util.Calendar;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class Factuur {
    private int id;
    private Calendar factuurdatum;
    private Calendar vervaldatum;
    private Debiteur debiteur;
    private OrderRegel orderRegel;
    private String opmerking;
    private String notitie;
    private Organisatie organisatie;
    FactuurDAO factuurDAO;

    public Factuur() {
        factuurDAO = new FactuurDAO();
    }

    public int getId() {
        return id;
    }
    public Factuur getFactuurById() {
        factuurDAO.getFactuur(getId());
    }
    public Factuur getAllFacturen() {
        factuurDAO.getAllFacturen();
    }

    public Calendar getFactuurdatum() {
        return factuurdatum;
    }

    public Calendar getVervaldatum() {
        return vervaldatum;
    }

    public Debiteur getDebiteur() {
        return debiteur;
    }

    public OrderRegel getOrderRegel() {
        return orderRegel;
    }

    public Organisatie getOrganisatie() {
        return organisatie;
    }

    public String getOpmerking() {
        return opmerking;
    }

    public String getNotitie() {
        return notitie;
    }

    public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }

    public void setNotitie(String notitie) {
        this.notitie = notitie;
    }

    public void deleteFactuurFromDatabaseById() {
        factuurDAO.deleteFactuur(getId());
    }

    public void addFactuurToDatabase() {
        factuurDAO.saveFactuur(factuurdatum, vervaldatum, debiteur, orderRegel, opmerking, notitie, organisatie);
    }
}


