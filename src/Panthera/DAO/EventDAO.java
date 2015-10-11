package Panthera.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Panthera.Models.Debiteur;
import Panthera.Models.Event;

/**
 * 
 * @author Roy
 *
 */
public class EventDAO extends DAO {

	public EventDAO() throws IllegalAccessException, InstantiationException, SQLException {
		super();
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
			event.setName(result.getString("naam"));
			event.setDate(result.getDate("datum"));
		}
		return event;
	}
	
	/**
	 * Set user present at given event.
	 * @param debiteur, event
	 * @param event
	 * @throws SQLException 
	 */
	public void setEvent(Debiteur debiteur, Event event) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = ("INSERT INTO aanwezig (event_id, debiteur_id) VALUES( "
				+ event.getId() + ", "
				+ debiteur.getId()
				+ ");");
		System.out.println(query);
		stmt.execute(query);
	}

}
