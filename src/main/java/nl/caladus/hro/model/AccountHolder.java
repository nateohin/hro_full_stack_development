package nl.caladus.hro.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class AccountHolder extends BaseEntity {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    public AccountHolder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AccountHolder() {
        // default no args constructor
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + getId() + '\'' +
                ", create=" + getCreate() + '\'' +
                ", lastModified=" + getLastModified() + '\'' +
                ", version=" + getVersion() +
                '}';
    }
}
