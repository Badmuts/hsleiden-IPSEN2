package Panthera.DAO;

import Panthera.Models.Factuur;
import Panthera.Models.Factuurregel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * @author Brandon
 * Created by Brandon on 22-Sep-15.
 * Deze klasse wordt gebruikt om gegevens op te halen uit de database
 * Maar ook om gegevens op te slaan in de database
 */
public class FactuurDAO extends DAO {

    /**
     * @author Brandon van Wijk
     * Dit is de standaard constructor om een instantie te kunnen maken van de DAO
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InstantiationException
     */
    public FactuurDAO() throws IllegalAccessException, SQLException, InstantiationException {
        super();
    }

    /**
     * @author Brandon van Wijk
     * Deze methode haalt 1 specifieke factuur op uit de database aan de hand van het id
     * Vervolgens wordt er een nieuwe factuur object aangemaakt
     * En gevuld met de data vanuit de database
     * @param id
     * @return
     * @throws Exception
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
     * @author Brandon van Wijk
     * Deze methode krijgt een factuur object mee en een string
     * De factuur is om te specifieceren om welke factuur het gaat
     * De string is de nieuwe status die aan de factuur gegeven wordt
     * Vervolgens wordt de factuur geupdatet in de database
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
     * @author Brandon van Wijk
     * @author Daan Rosbergen
     * Deze methode haalt alle factuur info op uit de database
     * Zowel de info van de order en de info van de producten op de order
     * Vervolgens wordt de lijst factuurregels die zich in factuur bevindt
     * Gevuld met de benodigde data uit de database
     * @return ArrayList<Factuur> List met factuurmodellen
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
     * @author Brandon van Wijk
     * Deze methode verwijdert een factuur uit de database
     * Er komt een factuur object binnen als parameter om
     * Duidelijk te maken om welke factuur het gaat aan de hand van zijn id
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
     * @author Brandon van Wijk
     * Deze methode slaat een factuur op in de database
     * Er komt een factuur object binnen als parameter
     * Om zo de data uit het object op te kunnen halen
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
     * @author Brandon van Wijk
     * Deze methode slaat de factuurregels van een object op
     * Zoals het factuur id, het aantal aangegeven wijnen op de factuur en het product id
     * De methode krijgt een factuur object binnen om de benodigde data te kunnen vragen
     * Aan het factuur object
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
     * @author Brandon van Wijk
     * Deze methode haalt het laatste id op
     * Dat is toegevoegd in de factuur tabel
     * Vervolgens wordt er bij dat id 1 opgeteld
     * Dit wordt gedaan zodat we geen conflicts krijgen met id's
     * @return id
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
     * @author Brandon van Wijk
     * Deze methode haalt het laatste factuurnummer op uit de database
     * En telt er 1 bij op dit doen we omdat we zo een default factuurnummer
     * kunnen laten zien bij het aanmaken van een factuur in de applicatie
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



