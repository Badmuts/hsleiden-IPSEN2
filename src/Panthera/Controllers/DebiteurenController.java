package Panthera.Controllers;

import Panthera.DAO.DebiteurDAO;
/**
 * 
 * @author Victor
 *
 */
public class DebiteurenController {
	
	private DebiteurDAO dao;
	
	public DebiteurenController() throws Exception {
		
		this.dao = new DebiteurDAO();
		this.view = new DebiteurListView();
	}
	
	public ObservableList<Debiteur> cmdGetDebiteuren() {
		ArrayList<Debiteur> debiteuren = new ArrayList<>();
		try {
			debiteuren.addAll(dao.getAllDebiteuren());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(debiteuren);
	}
	public void cmdDeleteDebiteur(ObservableList<Debiteur> debiteuren) {
		try {
			for(Debiteur debiteur: debiteuren) {
				if(debiteur.isChecked()) {
					dao.deleteDebiteur(debiteur.getId);
					Platform.runLater(() -> {
						debiteuren.remove(debiteur);
					});
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void cmdAddDebiteur(Debiteur debiteur) {
		try {
			dao.addDebiteur(debiteur);
			setView(new DebiteurenListView(this)).show();
		} catch (Exception e){
			e.printStackTrace;
		}
	}

}
