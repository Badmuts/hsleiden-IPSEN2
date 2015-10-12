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

	public SettingsController(MainController mainController) {
		this.mainController = mainController;

		try {
			this.dao = new SettingsDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Settings> cmdGetSettings() {
		ArrayList<Settings> settings = new ArrayList<>();
		try {
			settings.addAll(dao.getAllSettings());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FXCollections.observableArrayList(settings);
	}

	public void cmdUpdateSettings(Settings settings) {
		try {
			dao.updateSettings(settings);
			mainController.setSubview(new SettingsListView(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		this.mainController.setSubview(new SettingsListView(this));
	}

	public MainController getMainController() {
		return mainController;
	}

}
