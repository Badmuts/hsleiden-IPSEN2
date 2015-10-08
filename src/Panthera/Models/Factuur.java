package Panthera.Models;

import Panthera.DAO.FactuurDAO;
import Panthera.PDF.FirstPDF;
import com.itextpdf.text.pdf.PdfObject;
import javafx.beans.property.*;
import javafx.scene.control.DatePicker;

import java.sql.Date;
import java.time.LocalDate;
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
    private SimpleStringProperty opmerking;
    private SimpleDoubleProperty bedrag;
    private SimpleObjectProperty<FirstPDF> pdf;
    private SimpleStringProperty pdfPath;
    //private String notitie;
    //private Organisatie organisatie;


    public Factuur(int id, int factuurnummer, Date factuurdatum, Date vervaldatum, String status, Debiteur debiteur, ArrayList<Factuurregel> factuurregels, String opmerking) {

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
        this.pdfPath = new SimpleStringProperty();
       // this.notitie = notitie;
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
       // this.notitie = notitie;
    }

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

    public String getOpmerking() {
        return opmerking.get();
    }

    public Double getBedrag() { return bedrag.get(); }

    public FirstPDF getPDF() {
        return this.pdf.get();
    }

    public String getPdfPath() {
        return this.pdfPath.get();
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

    public void setOpmerking(String opmerking) {
        this.opmerking.set(opmerking);
    }

    public void setPDF(FirstPDF pdf) {
        this.pdf.set(pdf);
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath.set(pdfPath);
    }

    /*
    * TODO: bedrag setten vanit factuurregel
        public void setBedrag() {
            for(Factuurregel factuurregel: factuurregels) {
                if(null == null) {
                    this.bedrag.set(factuurregel.getTotaal());
                }
            }
        }
    */

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

    public SimpleStringProperty opmerkingProperty() { return this.opmerking; }

    public SimpleObjectProperty pdfProperty() { return this.pdf; }


//    public String getNotitie() {
//        return notitie;
//    }

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


