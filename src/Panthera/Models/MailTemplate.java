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
     * @author Daan Rosbergen
     * @return
     */
    public int getId() {
        return id.get();
    }

    /**
     * Property used to observe the value of id.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     * Set id.
     *
     * @author Daan Rosbergen
     * @param id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * Get name.
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     * Property used to observe name.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty nameProperty() {
        return name;
    }

    /**
     * Set name.
     *
     * @author Daan Rosbergen
     * @param name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Return the content of the mailtemplate.
     *
     * @author Daan Rosbergen
     * @return
     */
    public String getContent() {
        return content.get();
    }

    /**
     * Property used to observe content.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleStringProperty contentProperty() {
        return content;
    }

    /**
     * Set content of mail template.
     *
     * @author Daan Rosbergen
     * @param content
     */
    public void setContent(String content) {
        this.content.set(content);
    }

    /**
     * Get active state.
     *
     * @author Daan Rosbergen
     * @return
     */
    public boolean getActive() {
        return active.get();
    }

    /**
     * Get active property.
     *
     * @author Daan Rosbergen
     * @return
     */
    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    /**
     * Set template active.
     *
     * @author Daan Rosbergen
     * @param active
     */
    public void setActive(boolean active) {
        this.active.set(active);
    }

    /**
     * @author Daan Rosbergen
     * @return
     */
    @Override public String toString() {
        return name.get();
    }

}
