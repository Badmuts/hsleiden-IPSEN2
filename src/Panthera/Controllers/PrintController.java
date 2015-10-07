package Panthera.Controllers;

import java.util.List;

import Panthera.Models.AttributeCompare;
import Panthera.Models.Bestellijst;
import Panthera.Models.Debiteur;
import Panthera.Models.Printer;
import Panthera.Views.PrintSelectieView;

/**
 * Handles printing logic.
 * @author Roy i ii iii iv v vi vii viii ix x xi xii xiii xiv xv xvi xvii xviii xix xx. \\|//MM\\|//
 */
public class PrintController extends Controller {
	private List<Bestellijst> bestellijsten;
	private DebiteurenController debiteurenController;
	private Printer printer;
	
	/**
	 * Store the selected bestellijsten,
	 * open debiteurselectie window.
	 * @param bestellijsten
	 */
	public void print(List<Bestellijst> bestellijsten) {
		this.bestellijsten = bestellijsten;
		this.debiteurenController = new DebiteurenController();
		this.printer = new Printer();
		setView(new PrintSelectieView(this, debiteurenController.cmdGetDebiteuren())).show();
	}
	
	/**
	 * filter the list
	 * print it
	 * @param debiteuren
	 */
	public void printSelected(List<Debiteur> debiteuren) {
		debiteuren = debiteurenController.filterUnselected(debiteuren);
		for(Debiteur debiteur : debiteuren) {
			printer.print(debiteur);
		}
	}
	
	/**
	 * Order and print all
	 */
	public void printAll(List<Debiteur> debiteuren) {
		debiteuren.sort(new AttributeCompare());
		for(Debiteur debiteur : debiteuren) {
			printer.print(debiteur);
		}
	}
	
}