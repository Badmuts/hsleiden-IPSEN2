package Panthera.DAO;

import Panthera.Models.MailTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MailTemplatesDAO extends DAO {

    public MailTemplatesDAO() throws IllegalAccessException, InstantiationException, SQLException {
        super();
    }

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
