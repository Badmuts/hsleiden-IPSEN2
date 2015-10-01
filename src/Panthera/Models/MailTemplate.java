package Panthera.Models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MailTemplate {

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

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public boolean getActive() {
        return active.get();
    }

    public SimpleBooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }
}
