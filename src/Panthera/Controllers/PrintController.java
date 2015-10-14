package Panthera.Controllers;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import Panthera.Models.AttributeCompare;
import Panthera.Models.Bestellijst;
import Panthera.Models.BestellijstPdf;
import Panthera.Models.Debiteur;
import Panthera.Models.Printer;
import Panthera.Views.BestellijstenSummaryView;
import Panthera.Views.PrintSelectieView;

/**
 * Handles printing logic.
 * @author Roy i ii iii iv v vi vii viii ix x xi xii xiii xiv xv xvi xvii xviii xix xx. Might be bored of printing.
 */
public class PrintController extends Controller {
	private List<Bestellijst> bestellijsten;
	private DebiteurenController debiteurenController;
	private Printer printer;
	private MainController mainController;
	private BestellijstPdf bestellijstPdf;
	
	/**
	 * Store the selected bestellijsten,
	 * open debiteurselectie window.
	 * @param bestellijsten
	 */
	public void print(List<Bestellijst> bestellijsten, MainController mainController) throws Exception {
		this.bestellijsten = bestellijsten;
		this.mainController = mainController;
		this.debiteurenController = new DebiteurenController(mainController);
		this.printer = new Printer();
		this.bestellijstPdf = new BestellijstPdf();
		//setView(new PrintSelectieView(this, debiteurenController.cmdGetDebiteuren())).show();
		if(bestellijsten.size() < 1) {
			throw new Exception();
		}
		System.out.println(bestellijsten.size());
		mainController.setSubview(new PrintSelectieView(this, debiteurenController.cmdGetDebiteuren()));
	}
	
	/**
	 * filter the list
	 * generate PDF of selected.
	 * print it
	 * @param debiteuren
	 * @throws Exception 
	 */
	public void printSelected(List<Debiteur> debiteuren) throws Exception {
		debiteuren = debiteurenController.filterUnselected(debiteuren);
		try {
			if(debiteuren.size() < 1) {
				throw new Exception();
			}
			String path = generatePdf(bestellijsten, debiteuren);
			printer.print(path);
			cancel();
		} catch (FileNotFoundException | DocumentException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * generate PDF for all
	 * Order and print all
	 * @throws Exception 
	 */
	public void printAll(List<Debiteur> debiteuren) throws Exception {
		debiteuren.sort(new AttributeCompare());
		try {
			if(debiteuren.size() < 1) {
				throw new Exception();
			}
			String path = generatePdf(bestellijsten, debiteuren);
			printer.print(path);
			cancel();
		} catch (FileNotFoundException | DocumentException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print pdf for selected debiteurs.
	 * @throws DocumentException 
	 * @throws FileNotFoundException 
	 * @throws SQLException 
	 */
	public String generatePdf(List<Bestellijst> bestellijsten, List<Debiteur> debiteuren) throws FileNotFoundException, DocumentException, SQLException {
		String  path = bestellijstPdf.create(bestellijsten, debiteuren);
		return path;
	}
	
	/**
	 * Cancel printing, return to bestellijstOverview.
	 */
	public void cancel() {
		mainController.setSubview(new BestellijstenSummaryView(new BestellijstenController(mainController), mainController));
	}
	
}