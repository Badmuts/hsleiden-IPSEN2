package Panthera.DAO;

import Panthera.Models.Factuur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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


    /**
     * Returns a list with Factuur models with a limit of 25.
     *
     * @return ArrayList<Factuur> List with product models.
     * @throws Exception
     */
    public ArrayList<Factuur> getAllFacturen() throws Exception {
        ArrayList<Factuur> facturen = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT id, factuurnummer, factuurdatum, vervaldatum, status FROM factuur LIMIT 25");
        while (result.next()) {
            facturen.add(new Factuur(
                    result.getInt("id"),
                    result.getInt("factuurnummer"),
                    result.getDate("factuurdatum"),
                    result.getDate("vervaldatum"),
                    result.getString("status")));

        }
        return facturen;
    }
}
//
//    public void deleteFactuur(int id) throws SQLException {
//        //query om een factuur te verwijderen
//        try (Statement stmt = conn.createStatement()) {
//            stmt.executeQuery("DELETE * FROM factuur WHERE factuur_id = " + id);
//        }


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

