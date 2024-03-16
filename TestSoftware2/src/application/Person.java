package application;

import javafx.beans.property.SimpleStringProperty;

public class Person {

	private final SimpleStringProperty email;
    private final SimpleStringProperty status;

    public Person(String email, String status) {
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
    }

    public String getEmail() {
        return email.get();
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
    
    public SimpleStringProperty statusProperty() {
        return status;
    }
    
    public void setStatus(String newStatus) {
        status.set(newStatus);
    }
}
