package Panthera.DAO;

import Panthera.Models.Product;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO extends DAO {

    public ProductDAO() throws Exception {
        super();
    }

    /**
     * Get a Product model by id with values from the DB.
     *
     * @param id id of record in database.
     * @return Product Product model.
     * @throws Exception
     */
    public Product get(int id) throws Exception {
        Product product = new Product();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT productnummer, naam, jaar, prijs, type FROM product WHERE id =" + id);
        while (result.next()) {
            product.setProductnummer(result.getInt("productnummer"));
            product.setNaam(result.getString("naam"));
            product.setJaar(result.getInt("jaar"));
            product.setPrijs(result.getDouble("prijs"));
            product.setType(result.getString("type"));
        }
        return product;
    }

    /**
     * Returns a list with Product models with a limit of 25.
     *
     * @return ArrayList<Product> List with product models.
     * @throws Exception
     */
    public ArrayList<Product> all() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM product LIMIT 25");
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

    @Override public String toString() {
        return "ProductDAO{}";
    }

    public int save(Product product) throws Exception {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate("" +
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
