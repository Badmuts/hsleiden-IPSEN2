package Panthera.DAO;

import Panthera.Models.Factuur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * Created by Brandon on 22-Sep-15.
 */
public class FactuurDAO extends DAO {

    public FactuurDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
    }

    public Factuur getFactuur(int id) throws SQLException {
        //query om een specifieke factuur op te halen
        Factuur factuur = new Factuur();
        try (Statement stmt = conn.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM factuur WHERE id =" + id);
            while (result.next()) {
                factuur.setId(result.getInt("id"));
                factuur.setFactuurdatum(result.getDate("factuurdatum"));
                factuur.setVervaldatum(result.getDate("vervaldatum"));
                factuur.setStatus(result.getString("status"));
            }
        }
        return factuur;
    }

    public List<Factuur> getAllFacturen() throws SQLException {
        //query om alle facturen op te halen
        try (Statement stmt = conn.createStatement()) {
            stmt.executeQuery("SELECT * FROM factuur");
        }
        return null;
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
