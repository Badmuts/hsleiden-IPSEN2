package Panthera.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;

import Panthera.Models.Debiteur;
import Panthera.Models.Event;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * 
 * @author Roy
 *
 */
public class EventDAO extends DAO {
	private PreparedStatement setEvent;
	private PreparedStatement unsetEvent;
	private PreparedStatement getAanwezig;

	public EventDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		createPreparedStatements();
	}
	
	public void createPreparedStatements() {
		try {
			setEvent = conn.prepareStatement("INSERT INTO aanwezig(event_id, debiteur_id) SELECT ?, ? WHERE NOT EXISTS ( SELECT event_id FROM aanwezig WHERE event_id = ? AND debiteur_id = ? );");
			unsetEvent = conn.prepareStatement("DELETE FROM aanwezig WHERE event_id = ? AND debiteur_id = ?;");
			getAanwezig = conn.prepareStatement("SELECT debiteur.id AS debiteur, event.id AS event FROM debiteur, event, aanwezig WHERE debiteur.id = aanwezig.debiteur_id AND event.id = aanwezig.event_id AND event.id = ?;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the last event in the database.
	 * @return Event
	 * @throws SQLException
	 */
	public Event getLastEvent() throws SQLException {
		Event event = new Event();
		Statement stmt = conn.createStatement();
		String query = "SELECT * FROM event WHERE (datum) in (SELECT MAX(datum)	FROM event);";
		ResultSet result = stmt.executeQuery(query);
		while(result.next()) {
			event.setId(result.getInt("id"));
			event.setDate(result.getDate("datum"));
		}
		return event;
	}
	
	/**
	 * Checks if debiteur was at last event,
	 * sets debiteur isPresent to true if so.
	 * @param debiteur
	 * @throws SQLException 
	 */
	public void setAanwezig(List<Debiteur> debiteuren) throws SQLException {
		Event event = getLastEvent();
		getAanwezig.setInt(1, event.getId());
		ResultSet result = getAanwezig.executeQuery();
		while(result.next()) {
			//Check if debiteur is found in given list by id, return it, setPresent on it.
			try {
				getById(debiteuren, result.getInt("debiteur")).setPresent(new SimpleBooleanProperty(true));
			} catch(NoSuchElementException ex) {
				ex.printStackTrace();
				System.out.println("Debiteur not found in setAanwezig method.");
			}
		}
	}
	
	private Debiteur getById(List<Debiteur> debiteuren, int id) throws NoSuchElementException {
		for(Debiteur debiteur : debiteuren) {
			if(debiteur.getId() == id) {
				return debiteur;
			}
		}
		throw new NoSuchElementException();
	}
	
	/**
	 * Set user present at given event.
	 * @param debiteur, event
	 * @param event
	 * @throws SQLException 
	 */
	public void setEvent(Debiteur debiteur, Event event) throws SQLException {
		setEvent.setInt(1, event.getId());
		setEvent.setInt(2, debiteur.getId());
		setEvent.setInt(3, event.getId());
		setEvent.setInt(4, debiteur.getId());
		setEvent.execute();
	}
	
	/**
	 * Delete user present at given event.
	 * By removing debiteur from aanweizg table for given event.
	 * @param debiteur
	 * @param event
	 * @throws SQLException
	 */
	public void unsetEvent(Debiteur debiteur, Event event) throws SQLException{
		unsetEvent.setInt(1, event.getId());
		unsetEvent.setInt(2, debiteur.getId());
		unsetEvent.execute();
	}
	
}
