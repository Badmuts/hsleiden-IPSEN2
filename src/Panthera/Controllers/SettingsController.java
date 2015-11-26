package Panthera.Controllers;

import java.util.ArrayList;

import Panthera.DAO.SettingsDAO;
import Panthera.Models.Settings;
import Panthera.Views.SettingsListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Victor
 */

public class SettingsController extends Controller {

	private final MainController mainController;
	private SettingsDAO dao;
	
	/**
	 * @author Victor
	 * Maakt SettingsDAO aan, voegt een nieuw MainController object aan settingsController toe
	 * @param mainController
	 */
	public SettingsController(MainController mainController) {
		this.mainController = mainController;

		try {
			this.dao = new SettingsDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author Victor
	 * Haalt alle Settings objecten op.
	 * @return observableArrayList settings
	 */
	public ObservableList<Settings> cmdGetSettings() {
		ArrayList<Settings> settings = new ArrayList<>();
		try {
			settings.addAll(dao.getAllSettings());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(settings);
	}
	/**
	 * @author Victor
	 * Slaat settings object op
	 * @param settings
	 */
	public void cmdSaveSettings(Settings settings) {
		try {
			dao.saveSettings(settings);
			mainController.setSubview(new SettingsListView(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		this.mainController.setSubview(new SettingsListView(this));
	}
	/**
	 * @author Victor
	 * Geeft mainController object terug.
	 * @return
	 */
	public MainController getMainController() {
		return mainController;
	}
	/**
	 * @author Victor
	 * Verwijdert de Settings object die als parameter meegegeven wordt.
	 * @param settings
	 */
	public void cmdRemoveSettings(Settings settings) {
		try {
			dao.removeSettings(settings);
			mainController.setSubview(new SettingsListView(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
