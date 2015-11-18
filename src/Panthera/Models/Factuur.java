package Panthera.Models;

import Panthera.DAO.FactuurDAO;
import Panthera.PDFModels.FactuurPdf;
import javafx.beans.property.*;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Brandon on 22-Sep-15.
 * Dit is de model klasse van Factuur
 * Deze klasse wordt gebruikt om factuur objecten te kunnen maken
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
    private SimpleStringProperty opmerking;
    private SimpleDoubleProperty bedrag;
    private SimpleObjectProperty<FactuurPdf> pdf;
    private SimpleStringProperty pdfPath;
    private SimpleBooleanProperty betaald;

    //private Organisatie organisatie;

    /**
     * @author Brandon van Wijk
     * Deze constructor wordt gebruikt bij de pagina die een factuur aanmaakt.
     * Alle velden die in de applicatie worden ingevuld komen hier binnen in de constructor
     * @param id
     * @param factuurnummer
     * @param factuurdatum
     * @param vervaldatum
     * @param status
     * @param pdfpath
     * @param debiteur
     * @param factuurregels
     * @param opmerking
     */
    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, String pdfpath, Debiteur debiteur, ArrayList<Factuurregel> factuurregels, String opmerking) {

        this.id = new SimpleIntegerProperty(id);
        this.factuurnummer = new SimpleIntegerProperty(factuurnummer);
        this.factuurdatum = new SimpleObjectProperty<>(factuurdatum);
        this.vervaldatum = new SimpleObjectProperty<>(vervaldatum);
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        this.debiteur = new SimpleObjectProperty<>(debiteur);
        this.factuurregels = new SimpleObjectProperty<>(factuurregels);
        this.opmerking = new SimpleStringProperty(opmerking);
        this.pdf = new SimpleObjectProperty<>();
        this.pdfPath = new SimpleStringProperty(pdfpath);
        this.bedrag = new SimpleDoubleProperty();
        berekenBedrag();

    }

    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, String pdfpath, Debiteur debiteur) {

        this.id = new SimpleIntegerProperty(id);
        this.factuurnummer = new SimpleIntegerProperty(factuurnummer);
        this.factuurdatum = new SimpleObjectProperty<>(factuurdatum);
        this.vervaldatum = new SimpleObjectProperty<>(vervaldatum);
        this.status = new SimpleStringProperty(status);
        this.checked = new SimpleBooleanProperty(false);
        this.debiteur = new SimpleObjectProperty<>(debiteur);
        this.factuurregels = new SimpleObjectProperty<>(new ArrayList<>());
        this.opmerking = new SimpleStringProperty();
        this.bedrag = new SimpleDoubleProperty();
        this.pdf = new SimpleObjectProperty<>();
        this.pdfPath = new SimpleStringProperty(pdfpath);
        this.betaald = new SimpleBooleanProperty(false);
        berekenBedrag();
    }

    /**
     * @author Brandon van Wijk
     * Dit is de standaard constructor om een factuur object aan te maken
     * Er hoeven hier geen parameters te worden meegegeven
     * Wel vult hij alle attributen om nullpointer exceptions te voorkomen
     * @throws Exception
     */
    public Factuur() throws Exception {
        this.id = new SimpleIntegerProperty();
        this.factuurnummer = new SimpleIntegerProperty(new FactuurDAO().getLastFactuurNummer());
        this.factuurdatum = new SimpleObjectProperty<>();
        this.vervaldatum = new SimpleObjectProperty<>();
        this.status = new SimpleStringProperty("");
        this.checked = new SimpleBooleanProperty();
        this.debiteur = new SimpleObjectProperty<>();
        this.factuurregels = new SimpleObjectProperty<>(new ArrayList<Factuurregel>());
        this.opmerking = new SimpleStringProperty();
        this.bedrag = new SimpleDoubleProperty();
        this.pdf = new SimpleObjectProperty<>();
        this.pdfPath = new SimpleStringProperty();
        this.bedrag = new SimpleDoubleProperty();
        berekenBedrag();
    }


    /**
     * @author Brandon van Wijk
     * Hieronder staan alle get methodes
     * Deze worden gebruikt in deze en/of andere klasses om
     * De private atributen te kunnen bereiken en gebruiken
     * @return
     */
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

    public String getOpmerking() {
        return opmerking.get();
    }

    public Double getBedrag() {
        return bedrag.get(); }

    public FactuurPdf getPDF() {
        return this.pdf.get();
    }

    public String getPdfPath() {
        return this.pdfPath.get();
    }


    public ArrayList<Factuurregel> getFactuurregels() {
        return factuurregels.get();
    }

    /**
     * @author Brandon van Wijk
     * Hieronder alle set methodes
     * Deze worden gebruikt om de attributen te kunnen aanpassen
     */
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

    public void setOpmerking(String opmerking) {
        this.opmerking.set(opmerking);
    }

    public void setPDF(FactuurPdf pdf) {
        this.pdf.set(pdf);
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath.set(pdfPath);
    }

    public void setBedrag(double bedrag) {
        this.bedrag.set(bedrag);
    }

    public void setFactuurregels(ArrayList<Factuurregel> factuurregels) {
        this.factuurregels.set(factuurregels);
    }

    public void setBetaald(Boolean betaald) {
        this.betaald.set(betaald);
    }


    /**
     * @author Brandon van Wijk
     * Hieronder staan alle property methodes
     * Deze worden gebruikt in de tableviews in de applicatie
     * @return
     */
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

    public Boolean isBetaald() {
        return this.betaald.get();
    }

    public SimpleBooleanProperty betaaldProperty() {
        return this.betaald;
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public SimpleBooleanProperty checkedProperty() {
        return this.checked;
    }

    public SimpleObjectProperty debiteurProperty() { return this.debiteur; }

    public SimpleStringProperty opmerkingProperty() { return this.opmerking; }

    public SimpleObjectProperty pdfProperty() { return this.pdf; }

    public SimpleDoubleProperty bedragProperty() { return this.bedrag; }

    public SimpleObjectProperty<ArrayList<Factuurregel>> factuurregelsProperty() {
        return factuurregels;
    }


    /**
     * @author Brandon van Wijk
     * Deze methode wordt gebruikt bij het aanmaken van een factuur
     * Er wordt dan ook gelijk een factuuregel toegevoegd
     * @param factuurregel
     */
    public void addFactuurregel(Factuurregel factuurregel) {
        this.factuurregels.get().add(factuurregel);
    }

    /**
     * @author Brandon van Wijk
     * Deze methode loopt door de lijst van factuurregels heen
     * En zet het totale bedrag van de factuur
     * Dit bedrag wordt vervolgens opgehaald in het factuuroverzicht
     */
    public void berekenBedrag() {
        for(Factuurregel factuurregel: factuurregels.getValue()) {
            setBedrag(factuurregel.getAantal() * factuurregel.getPrijs());
        }
    }

    /**
     * @author Brandon van Wijk
     * Deze methode returned een string met factuurdata
     * @return String
     */
    public String toString() {
        return "Factuur: " + this.factuurnummer + " " + this.factuurdatum + " " + this.vervaldatum + " " + this.status;
    }

}


