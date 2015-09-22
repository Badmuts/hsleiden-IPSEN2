package Panthera.DAO;

import Panthera.Models.Factuur;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import static Panthera.Services.DatabaseService.*;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class FactuurDAO extends DAO {

    FactuurDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
    }

    public Factuur getFactuur(int id) throws SQLException {
        //query om een specifieke factuur op te halen
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT * FROM factuur WHERE factuur_id =" + id);
        }
    }

    public List<Factuur> getAllFacturen() throws SQLException {
        //query om alle facturen op te halen
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT * FROM factuur");
        }
    }

    public void deleteFactuur(int id) throws SQLException {
        //query om een factuur te verwijderen
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("DELETE * FROM factuur WHERE factuur_id = " + id);
        }
    }

   /* public void saveFactuur(Calendar factuurdatum, Calendar vervaldatum, Debiteur debiteur, OrderRegel orderRegel, String opmerking, String notitie, Organisatie organisatie) throws SQLException {
        //query om een factuur op te slaan
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery( "INSERT INTO factuur VALUES(" +
                            factuurdatum + "," +
                            vervaldatum + "," +
                            debiteur + "," +
                            orderRegel + "," +
                            opmerking + "," +
                            notitie + "," +
                            organisatie + ")"
                            );
        }
    } */
}
