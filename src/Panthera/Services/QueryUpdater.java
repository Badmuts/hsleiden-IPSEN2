package Panthera.Services;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Panthera.DAO.DAO;

/**
 * Executes the queries found in the source folder queries.
 * Database changes should go in there.
 * @author Roy
 *
 */
public class QueryUpdater extends DAO{
	public QueryUpdater() throws IllegalAccessException, InstantiationException, SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void update() {
		String query = getQuery();
		execute(query);
	}
	
	/**
	 * Execute the fetched query.
	 * @param query
	 */
	public void execute(String query) {
		try {
			Statement stmt = conn.createStatement();
			boolean result = stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Fetch query from update.txt file.
	 * @return query string.
	 */
	public String getQuery() {
		File file = new File("queries/update.txt");
		String query = "";
		try {
			Scanner scanner = new Scanner(file);
			while(scanner.hasNextLine()) {
				query += scanner.nextLine().toString() + " \n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return query;
	}
}