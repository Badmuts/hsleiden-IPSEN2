package Panthera.PDF;

import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import Panthera.Models.Debiteur;
import Panthera.Models.Factuur;
import Panthera.Models.Factuurregel;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class FirstPDF {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.NORMAL);


    private Factuur factuur;
    private ArrayList<Factuurregel> factuurregels = new ArrayList<>();
    private Debiteur debiteur;
    private Document document;
    private Paragraph preface;
    private Double totaalbedrag = 0.0;

    public FirstPDF(Factuur factuur, ArrayList<Factuurregel> factuurregels, Debiteur debiteur) {
        this.factuur = factuur;
        this.factuurregels = factuurregels;
        this.debiteur = debiteur;

        try {
            String FILE = "src/Panthera/PDF/PdfFiles/" + factuur.getFactuurnummer()+"-"+ debiteur.getNaam() +".pdf";
             this.document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
//            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }

    private void addTitlePage(Document document) throws DocumentException {

        preface = new Paragraph();
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(debiteur.getAanhef() + " " + debiteur.getVoornaam() + " " + debiteur.getTussenvoegsel() + " " + debiteur.getNaam(), subFont));
        preface.add(new Paragraph(debiteur.getAdres(), subFont));
        preface.add(new Paragraph(debiteur.getWoonplaats() + " " + debiteur.getPostcode(), subFont));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph(String.valueOf("Factuurdatum: " + factuur.getFactuurdatum().getDay() +"-"+ factuur.getFactuurdatum().getMonth() + "-" + factuur.getFactuurdatum().getYear()), smallBold));
        preface.add(new Paragraph(String.valueOf("Vervaldatum: " + factuur.getVervaldatum().getDay() +"-"+ factuur.getVervaldatum().getMonth() +"-"+ factuur.getVervaldatum().getYear()), smallBold));
        preface.add(new Paragraph(String.valueOf("Debiteurennummer: " + debiteur.getId()), smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Betreft: Wijnbestelling Benefiet Wijnfestijn Oud Poelgeest 22 september 2013" , smallBold));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Opmerking:"));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph(factuur.getOpmerking(), normalFont));
        preface.add(new Paragraph("Totaal:"  + this.totaalbedrag));
        document.add(preface);
        createTable();

    }

    private void createTable()
            throws BadElementException {
        PdfPTable table = new PdfPTable(4);

        float[] widths = new float[] { 90f, 30f, 30f, 30f };

        try {
            table.setWidths(widths);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        PdfPCell c1 = new PdfPCell(new Phrase("Product"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Aantal"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Btw"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prijs"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        int factuurRegelsSize = this.factuurregels.size();

        for(int i = 0; i < factuurRegelsSize; i++) {
            DecimalFormat df = new DecimalFormat("0.00");

            table.addCell(this.factuurregels.get(i).getProduct().getNaam());
            table.addCell(String.valueOf(this.factuurregels.get(i).getAantal()));
            table.addCell(String.valueOf(df.format(this.factuurregels.get(i).getBtw())));
            table.addCell(String.valueOf(df.format(this.factuurregels.get(i).getTotaal())));
        }

        for(Factuurregel factuurregel: factuurregels) {
            double totaal = 0.0;
            totaal += factuurregel.getSubtotaal();
            this.totaalbedrag += totaal;
        }

        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

//    private void createList(Section subCatPart) {
//        List list = new List(true, false, 10);
//        list.add(new ListItem("First point"));
//        list.add(new ListItem("Second point"));
//        list.add(new ListItem("Third point"));
//        subCatPart.add(list);
//    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}