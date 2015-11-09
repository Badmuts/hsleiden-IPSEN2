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

    /** constructor
     *
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException
     */
    public FactuurDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
    }
    /**
     *Methode om een enkele factuur op te halen uit de database aan de hand van een id
    */
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
     * methode om de status van een factuur te updaten in de database
     * @param factuur
     * @param status
     * @throws Exception
     */
    public void updateStatus(Factuur factuur , String status) throws Exception {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE factuur " +
                    "SET status='"+ status +"' WHERE id=" + factuur.getId());
        }

    /**
     * Returns a list with Factuur models.
     *  //Deze methode returned een arraylist met alle facturen aan de database
     * @return ArrayList<Factuur> List with product models.
     * @throws Exception
     */
    public ArrayList<Factuur> getAllFacturen() throws Exception {
        ArrayList<Factuur> facturen = new ArrayList<>();
        ArrayList<Factuurregel> factuurregels = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT f.id AS factuur_id,\n" +
                "  f.factuurnummer,\n" +
                "  f.debiteur_id,\n" +
                "  f.factuurdatum,\n" +
                "  f.vervaldatum,\n" +
                "  f.status,\n" +
                "  f.pdfpath,\n" +
                "  f.opmerking,\n" +
                "  o.aantal,\n" +
                "  p.id AS product_id,\n" +
                "  p.land_id\n" +
                "FROM factuur f,\n" +
                "  tbl_order o,\n" +
                "  product p\n" +
                "WHERE f.id = o.factuur_id\n" +
                "AND o.product_id = p.id\n" +
                "ORDER BY f.id;");
        int currFactuurId = 0;
        int prevFactuurId = currFactuurId;
        while (result.next()) {
            currFactuurId = result.getInt("factuur_id");
            factuurregels.add(new Factuurregel(result.getInt("aantal"), new ProductDAO().get(result.getInt("product_id"))));
            if (currFactuurId != prevFactuurId) {
                facturen.add(new Factuur(
                        result.getInt("factuur_id"),
                        result.getInt("factuurnummer"),
                        result.getDate("factuurdatum"),
                        result.getDate("vervaldatum"),
                        result.getString("status"),
                        result.getString("pdfpath"),
                        new DebiteurDAO().getDebiteur(result.getInt("debiteur_id")),
                        factuurregels,
                        result.getString("opmerking")));
                prevFactuurId = result.getInt("factuur_id");
            }
        }
        return facturen;
    }



    /**
     * Deze methode verwijderd de betreffende factuur uit de database.
     * @param factuur
     * @throws SQLException
     */
    public void deleteFactuur(Factuur factuur) throws SQLException {
        //query om een factuur te verwijderen
       Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM factuur " +
                "WHERE id=" + factuur.getId());
        }

    /**
     * Deze methode slaat een factuur en factuuregels op in de database
     * @param factuur
     * @throws Exception
     */
    public void save(Factuur factuur) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("" +
        "INSERT INTO factuur(factuurnummer, debiteur_id, factuurdatum, vervaldatum, opmerking, status, pdfpath)" +
            "VALUES(" +
            factuur.getFactuurnummer() + ", " +
            factuur.getDebiteur().getId() + ", '" +
            factuur.getFactuurdatum() + "', '" +
            factuur.getVervaldatum() + "',' " +
            factuur.getOpmerking() + "',' " +
            factuur.getStatus() + "','" +
            factuur.getPdfPath() + "')");
        saveFactuurregels(factuur);
    }

    /**
     * deze methode vult de kopeltabel tussen factuur en product
     * @param factuur
     * @throws Exception
     */
    private void saveFactuurregels(Factuur factuur) throws Exception {
        int factuurId = getLastFactuurId();
        for (Factuurregel factuurregel: factuur.getFactuurregels()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(""
                + "INSERT INTO tbl_order (factuur_id, aantal, product_id)"
                + "VALUES (" +
                + factuurId + ", " +
                + factuurregel.getAantal() + ", " +
                + factuurregel.getProduct().getId() + ")");
        }
    }

    /**
     * deze methode haalt het laatste factuur id op dat er in de database is bij gekomen
     * @return
     * @throws Exception
     */
    public int getLastFactuurId() throws Exception {
        int id = 0;
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT MAX(id) AS id FROM factuur");
        while (result.next()) {
            id = result.getInt("id");
        }
        return id++;
    }

    /**
     * Deze methode haalt het laastte factuurnummer op uit de database om deze te laten zien bij het aanmaken bij een factuur als voorbeeld
     * @return
     * @throws Exception
     */
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



