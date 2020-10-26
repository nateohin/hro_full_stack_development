package nl.caladus.hro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CombinedAccount extends BaseEntity {

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonIgnore
    Account account1;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonIgnore
    Account account2;

    String IBAN;

    public Account getAccount1() {
        return account1;
    }

    public void setAccount1(Account account1) {
        this.account1 = account1;
    }

    public Account getAccount2() {
        return account2;
    }

    public void setAccount2(Account account2) {
        this.account2 = account2;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
