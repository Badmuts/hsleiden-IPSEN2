package Panthera.DAO;

import Panthera.Models.MailTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to retrieve, manipulate and remove mail template models from the DB.
 *
 * @author Daan Rosbergen
 */
public class MailTemplatesDAO extends DAO {

    public MailTemplatesDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

    /**
     * Retrieve a MailTemplate Model by id from the DB.
     *
     * @author Daan Rosbergen
     * @param id            MailTemplate id in DB.
     * @return              MailTemplate model.
     * @throws Exception    Database error.
     */
    public MailTemplate get(int id) throws Exception {
        MailTemplate mail = new MailTemplate();
        java.sql.Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM mail_templates WHERE id =" + id);
        while (result.next()) {
            mail.setId(result.getInt("id"));
            mail.setName(result.getString("name"));
            mail.setContent(result.getString("content"));
            mail.setActive(result.getBoolean("active"));
        }
        return mail;
    }

    /**
     * Retrieve all MailTemplate Model from the DB in a ArrayList.
     *
     * @author Daan Rosbergen
     * @return              ArrayList containing MailTemplate models.
     * @throws Exception    Database error.
     */
    public List<MailTemplate> all() throws Exception {
        ArrayList<MailTemplate> mails = new ArrayList<>();
        java.sql.Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM mail_templates");
        while (result.next()) {
            MailTemplate mail = new MailTemplate();
            mail.setId(result.getInt("id"));
            mail.setName(result.getString("name"));
            mail.setContent(result.getString("content"));
            mail.setActive(result.getBoolean("active"));
            mails.add(mail);
        }
        return mails;
    }

}
