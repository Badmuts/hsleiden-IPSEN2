package Panthera.DAO;

import Panthera.Services.DatabaseService;

import java.lang.reflect.Constructor;
import java.sql.*;

/**
 * Created by Brandon on 22-Sep-15.
 */
public class DAO {

    protected String modelName;
    protected Connection conn;

    public DAO() throws IllegalAccessException, InstantiationException, SQLException {
        this.conn = DatabaseService.getInstance().getConnection();
        modelName = getClass().getSimpleName();
        modelName = modelName.replace("DAO", "");
        System.out.println(modelName);
    }

    public Object find(int id) throws Exception {
        Class<?> clazz = Class.forName("Panthera.Models." + modelName);
        Constructor<?> constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(
            "SELECT * FROM " + modelName + " WHERE id =" + id + " LIMIT 1");
        ResultSetMetaData resultMeta = result.getMetaData();
        while (result.next()) {
            for (int i = 1; i < resultMeta.getColumnCount(); i++) {
                String type = resultMeta.getColumnTypeName(i);
                String columnName = resultMeta.getColumnName(i);
                columnName = columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                Class[] argTypes = new Class[] { int.class };
                instance.getClass().getDeclaredMethod("set" + columnName, argTypes).invoke(instance, result.getInt(i));
            }
        }
        return instance;
    }
}
