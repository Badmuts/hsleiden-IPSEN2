package Panthera.Models;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * For actually printing something to irl printer.
 * @author Roy
 *
 */
public class Printer {
	private Desktop desktop;
	
	public void print(Debiteur debiteur) {
		System.out.println("Printing " + debiteur.getNaam());
		File file = new File("pdf/example.pdf");
		sendPrinter(file);
	}
	
	public void sendPrinter(File file) {
		desktop = desktop.getDesktop();
		try {
			desktop.print(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}