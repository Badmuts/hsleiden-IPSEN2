package Panthera.Models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Panthera.DAO.BestellijstDAO;

/**
 * Generates the desired Bestellijst pdf file.
 * @author Roy
 *
 */
public class BestellijstPdf {
	private List<Bestellijst> bestellijsten;
	private List<Debiteur> debiteuren;
	private BestellijstDAO bestellijstDao;
	private Font titleFont = FontFactory.getFont("Times-Roman", 16);
	private Font tableHeaderFont = FontFactory.getFont("Times-Roman", 12, Font.BOLD);
	private Font annotationFont = FontFactory.getFont("Times-Roman", 8);
	
	public BestellijstPdf() {
		try {
			this.bestellijstDao = new BestellijstDAO();
		} catch (IllegalAccessException | InstantiationException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a single pdf document with all bestellijst objects for all debiteur objects on a seperate page.
	 * @param bestellijsten
	 * @param debiteuren
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 * @throws SQLException
	 * @return FilePath String
	 */
	public String create(List<Bestellijst> bestellijsten, List<Debiteur> debiteuren) throws FileNotFoundException, DocumentException, SQLException {
		this.bestellijsten = bestellijsten;
		this.debiteuren = debiteuren;
		//TODO : make changing file path e.q. bestellijst1.pdf -> bestellijst2.pdf
		final String result = "pdf/bestellijst.pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(result));
		document.open();
		createPages(document);
		document.close();
		return result;
	}
	
	/**
	 * Create the bestellijst pages,
	 * add each table to new page of the pdf.
	 * For each Debiteur a page is created.
	 * @param document
	 * @throws DocumentException 
	 * @throws SQLException 
	 */
	private void createPages(Document document) throws DocumentException, SQLException {
		for(Debiteur debiteur : debiteuren) {
			createPageHeader(document, debiteur);
			for(Bestellijst bestellijst : bestellijsten) {
				document.add(createTable(bestellijst, debiteur));
				document.add(Chunk.NEWLINE);
			}
			document.newPage();
		}
	}
	
	/**
	 * Create the bestellijst PdfPTable for given Bestellijst.
	 * @param bestellijst
	 * @return PdfPTable
	 * @throws SQLException 
	 */
	private PdfPTable createTable(Bestellijst bestellijst, Debiteur debiteur) throws SQLException {
		List<Product> producten = bestellijstDao.getProducten(bestellijst);
		PdfPTable table = new PdfPTable(7);
		createTableHeader(table);
		for(Product product : producten) {
			table.addCell(String.valueOf(product.getId()));
			table.addCell(product.getNaam());
			table.addCell(String.valueOf(product.getJaar()));
			table.addCell(String.valueOf(product.getPrijs()));
			table.addCell(String.valueOf(product.getPrijs() * 6));
			table.addCell(product.getType());
			table.addCell("");
		}
		return table;
	}
	
	/**
	 * Create bestellijst tableheader.
	 * @param table
	 */
	private void createTableHeader(PdfPTable table) {
		table.addCell(new Phrase("Product", tableHeaderFont));
		table.addCell(new Phrase("Naam", tableHeaderFont));
		table.addCell(new Phrase("Jaar", tableHeaderFont));
		table.addCell(new Phrase("Prijs", tableHeaderFont));
		table.addCell(new Phrase("Prijs Doos", tableHeaderFont));
		table.addCell(new Phrase("Type", tableHeaderFont));
		table.addCell(new Phrase("aantal", tableHeaderFont));
	}
	
	/**
	 * Create the page title.
	 * @param document
	 * @param debiteur
	 * @throws DocumentException
	 */
	public void createPageHeader(Document document, Debiteur debiteur) throws DocumentException {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		document.add(new Paragraph(new Phrase("Bestellijst Benefiet Wijnfestijn", titleFont)));
		document.add(new Paragraph("Datum " + strDate));
		document.add(new Paragraph("Kasteel Oud Poelgeest"));
		document.add(new Paragraph("Naam: " + debiteur.getVoornaam() +" "+ debiteur.getTussenvoegsel() +" "+ debiteur.getNaam()));
		document.add(new Paragraph("Adres: " + debiteur.getAdres()));
		document.add(new Paragraph("Email: " + debiteur.getEmail()));
		document.add(new Paragraph(new Phrase("De kleinste besteleenheid is een doos bestaande uit zes flessen.", annotationFont)));
		document.add(Chunk.NEWLINE);
	}
}
