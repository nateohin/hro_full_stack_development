package nl.caladus.hro.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Embeddable
public class AccountHolder extends BaseEntity {

    @NotNull(message = "Firstname cannot be null")
    private String firstName;

    @NotNull(message = "Lastname cannot be null")
    private String lastName;

    private Gender gender;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender + '\'' +
                ", create=" + getCreate() + '\'' +
                ", lastModified=" + getLastModified() + '\'' +
                ", version=" + getVersion() +
                '}';
    }
}
