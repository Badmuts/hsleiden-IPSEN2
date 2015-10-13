package Panthera.Models;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * For generating the Inkoopfactuur PDf
 * @author Roy
 *
 */
public class InkoopfactuurPdf {
	
	public void create(List<InkoopfactuurRegel> regels) throws FileNotFoundException, DocumentException {
		final String result = "pdf/inkoopfactuur.pdf";
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(result));
		document.open();
		document.add(createTable(regels));
		document.close();
	}
	
	public PdfPTable createTable(List<InkoopfactuurRegel> regels) {
		PdfPTable table = new PdfPTable(4);
		createHeader(table);
		for(InkoopfactuurRegel regel : regels) {
			table.addCell(String.valueOf(regel.getProduct_id()));
			table.addCell(regel.getProductnaam());
			table.addCell(String.valueOf(regel.getAantal()));
			table.addCell(String.valueOf(calculatePrice(regel)));
		}
		createFooter(table, regels);
		return table;
	}
	
	public void createHeader(PdfPTable table) {
		table.addCell("Product_id");
		table.addCell("Product_naam");
		table.addCell("Product_aantal");
		table.addCell("Prijs");
	}
	
	public void createFooter(PdfPTable table, List<InkoopfactuurRegel> regels) {
		table.addCell("");
		table.addCell("");
		table.addCell("");
		table.addCell(String.valueOf(calculateTotalPrice(regels)));
	}
	
	public double calculatePrice(InkoopfactuurRegel regel) {
		double prijs;
		prijs = regel.getAantal() * regel.getPrijs();
		return prijs;
	}
	
	public double calculateTotalPrice(List<InkoopfactuurRegel> regels) {
		double prijs = 0.0;
		for(InkoopfactuurRegel regel : regels) {
			prijs += regel.getPrijs() * regel.getAantal();
		}
		
		return prijs;
	}

}
