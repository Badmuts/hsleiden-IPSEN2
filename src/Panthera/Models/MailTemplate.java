package Panthera.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model representing a MailTemplate.
 *
 * @author Daan Rosbergen
 */
public class MailTemplate extends Model {

    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty content;
    private SimpleBooleanProperty active;

    public MailTemplate() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        content = new SimpleStringProperty();
        active = new SimpleBooleanProperty();
    }

    public MailTemplate(int id, String name,
        String content, boolean active) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.content = new SimpleStringProperty(content);
        this.active = new SimpleBooleanProperty(active);
    }

    /**
     * Returns the mail template id.
     *
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Property used to observe the value of id.
     *
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     * Property used to observe name.
     *
     * @return
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * Set name.
     *
     * @param name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Return the content of the mailtemplate.
     *
     * @return
     */
    public String getContent() {
        return content.get();
    }

    /**
     * Property used to observe content.
     *
     * @return
     */
    public SimpleStringProperty contentProperty() {
        return content;
    }

    /**
     * Set content of mail template.
     *
     * @param content
     */
    public void setContent(String content) {
        this.content.set(content);
    }

    /**
     * Get active state.
     *
     * @return
     */
    public boolean getActive() {
        return active.get();
    }

    /**
     * Get active property.
     *
     * @return
     */
    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    /**
     * Set template active.
     *
     * @param active
     */
    public void setActive(boolean active) {
        this.active.set(active);
    }

    @Override public String toString() {
        return name.get();
    }

}
