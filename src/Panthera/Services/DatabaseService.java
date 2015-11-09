package Panthera.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Brandon on 22-Sep-15.
 */

public class DatabaseService {

    /**
     *  dit attribuut zorgt er voor dat er maar 1 thread is van connectionInstance
     */
    private volatile static DatabaseService connectionInstance;

    /**
     * private constructor zodat er geen instantie aangemaakt kan worden
     */
    private DatabaseService()  {
        //Hier wordt de driver gespecificeerd
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
            catch(IllegalAccessException ex) {
                System.out.println("Error: access problem while loading!");
                System.exit(2);
            }
                catch(InstantiationException ex){
                    System.out.println("Error: unable to instantiate driver!");
                    System.exit(3);
                }
            }


    /**
     * deze methode is om de instantie op te halen van de connectie die je wilt gebruiken in de DAO klassen
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static DatabaseService getInstance() throws InstantiationException, IllegalAccessException {
        if(connectionInstance == null) {
            synchronized (DatabaseService.class) {
                if(connectionInstance == null) {
                    connectionInstance = new DatabaseService();
                }
            }
        }
        return connectionInstance;
    }

    /**
     * deze methode haalt daadwerkelijk de connectie om op queries op uit te voeren in de DAO klassen
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        String URL = "jdbc:postgresql://localhost/Lions";
        Properties info = new Properties( );

        info.put( "user", "postgres" );
        info.put( "password", "Welkom#1" );

        Connection conn = DriverManager.getConnection(URL, info);

        return conn;
    }
}
