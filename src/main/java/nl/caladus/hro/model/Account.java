package nl.caladus.hro.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Account extends BaseEntity {

    @NotNull(message = "IBAN cannot be null")
    private String IBAN;

    @NotNull(message = "Amount cannot be null")
    private float amount;

    private boolean blocked = false;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @NotNull(message = "Account holder must not be null")
    private Set<AccountHolder> accountHolder;

    public Account() {
        // default no args constructor
    }

    public Account(String IBAN, float amount, Set<AccountHolder> accountHolder) {
        this.IBAN = IBAN;
        this.amount = amount;
        this.accountHolder = accountHolder;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Set<AccountHolder> getAccountHolders() {
        return accountHolder;
    }

    public void setAccountHolders(Set<AccountHolder> accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", amount=" + amount + '\'' +
                ", blocked=" + blocked + '\'' +
                ", accountHolder=" + accountHolder + '\'' +
                ", id=" + getId() + '\'' +
                ", create=" + getCreate() + '\'' +
                ", lastModified=" + getLastModified() + '\'' +
                ", version=" + getVersion() +
                '}';
    }
}
