package Panthera.DAO;

import Panthera.Models.Factuur;
import Panthera.Models.Factuurregel;

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

    public Factuur getFactuur(int id) throws Exception {
        //query om een specifieke factuur op te halen
        Factuur factuur = new Factuur();
        try (Statement stmt = conn.createStatement()) {
            ResultSet result = stmt.executeQuery("SELECT * FROM factuur WHERE id =" + id);
            while (result.next()) {
                factuur.setId(result.getInt("id"));
                factuur.setFactuurdatum(result.getDate("factuurdatum"));
                factuur.setVervaldatum(result.getDate("vervaldatum"));
                factuur.setStatus(result.getString("status"));
                factuur.setOpmerking(result.getString("opmerking"));
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
        ResultSet result = stmt.executeQuery("SELECT id, factuurnummer, debiteur_id, factuurdatum, vervaldatum, status, opmerking FROM factuur LIMIT 25");
        while (result.next()) {
            facturen.add(new Factuur(
                    result.getInt("id"),
                    result.getInt("factuurnummer"),
                    result.getDate("factuurdatum"),
                    result.getDate("vervaldatum"),
                    result.getString("status"),
                    new DebiteurDAO().getDebiteur(result.getInt("debiteur_id"))));

        }
        return facturen;
    }

    public void deleteFactuur(Factuur factuur) throws SQLException {
        //query om een factuur te verwijderen
       Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM factuur " +
                "WHERE id=" + factuur.getId());
        }

    public void save(Factuur factuur) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("" +
        "INSERT INTO factuur(factuurnummer, debiteur_id, factuurdatum, vervaldatum, opmerking, status)" +
            "VALUES(" +
            factuur.getFactuurnummer() + ", " +
            factuur.getDebiteur().getId() + ", '" +
            factuur.getFactuurdatum() + "', '" +
            factuur.getVervaldatum() + "',' " +
            factuur.getOpmerking() + "',' " +
            factuur.getStatus() + "')");
        saveFactuurregels(factuur);
    }

    private void saveFactuurregels(Factuur factuur) throws Exception {
        int factuurId = getLastFactuurId();
        for (Factuurregel factuurregel: factuur.getFactuurregels()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(""
                + "INSERT INTO factuurregel (factuur_id, aantal, product_id)"
                + "VALUES (" +
                + factuurId + ", " +
                + factuurregel.getAantal() + ", " +
                + factuurregel.getProduct().getId() + ")");
        }
    }

    public int getLastFactuurId() throws Exception {
        int id = 0;
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT MAX(id) AS id FROM factuur");
        while (result.next()) {
            id = result.getInt("id");
        }
        return id++;
    }

    public int getLastFactuurNummer() throws Exception {
        int factuurnummer = 0;

        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT MAX(factuurnummer) AS factuurnummer FROM factuur");
        while(result.next()) {
            factuurnummer = result.getInt("factuurnummer");
        }
        return factuurnummer += 1;
    }

}



