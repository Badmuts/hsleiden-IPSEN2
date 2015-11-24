package Panthera.DAO;

import Panthera.Models.Product;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class used to retrieve, manipulate and remove product models from the DB.
 *
 * @author Daan Rosbergen
 */
public class ProductDAO extends DAO {

    public ProductDAO() throws Exception {
        super();
    }

    /**
     * Get a Product model by id with values from the DB.
     *
     * @author Daan Rosbergen
     * @param id id of record in database.
     * @return Product Product model.
     * @throws Exception
     */
    public Product get(int id) throws Exception {
        Product product = new Product();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT productnummer, naam, jaar, prijs, type, land_id FROM product WHERE id =" + id);
        while (result.next()) {
            product.setProductnummer(result.getInt("productnummer"));
            product.setNaam(result.getString("naam"));
            product.setJaar(result.getInt("jaar"));
            product.setPrijs(result.getDouble("prijs"));
            product.setType(result.getString("type"));
            product.setLand(new LandDAO().get(result.getInt("land_id")));
        }
        return product;
    }

    /**
     * Returns a list with Product models.
     *
     * @author Daan Rosbergen
     * @return ArrayList<Product> List with product models.
     * @throws Exception
     */
    public ArrayList<Product> all() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM product");
        while (result.next()) {
            products.add(new Product(
                result.getInt("id"),
                result.getInt("productnummer"),
                result.getString("naam"),
                result.getInt("jaar"),
                result.getDouble("prijs"),
                result.getString("type"),
                new LandDAO().get(result.getInt("land_id"))));
        }
        return products;
    }

    /**
     * Saves a product model to the DB.
     *
     * @author Daan Rosbergen
     * @param product       Product model.
     * @throws Exception    Database error.
     */
    public void save(Product product) throws Exception {
        if (product.hasId()) {
            update(product);
        } else {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("" +
                "INSERT INTO product(productnummer, naam, jaar, prijs, type, land_id) " +
                "VALUES(" +
                product.getProductnummer() + ", '" +
                product.getNaam() + "', " +
                product.getJaar() + ", " +
                product.getPrijs()+ ", '" +
                product.getType() + "'," +
                product.getLand().getId() +")");
        }
    }

    /**
     * Updates product in DB according to Model passed in as argument.
     *
     * @author Daan Rosbergen
     * @param product Product model.
     */
    public void update(Product product) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE product " +
                "SET productnummer=" + product.getProductnummer() + ", " +
                "naam='" + product.getNaam() + "', " +
                "jaar=" + product.getJaar() + ", " +
                "prijs=" + product.getPrijs() + ", " +
                "type='" + product.getType() + "', " +
                "land_id=" + product.getLand().getId() + " " +
                "WHERE id=" + product.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove product form DB according to Model passed in as argument.
     *
     * @author Daan Rosbergen
     * @param product       Product model.
     * @throws Exception    Database error.
     */
    public void delete(Product product) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM product " +
            "WHERE id=" + product.getId());
    }
}
