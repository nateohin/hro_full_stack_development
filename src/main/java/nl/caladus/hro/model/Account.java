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

    private @NotNull(message = "Amount cannot be null") Float amount;

    private boolean blocked = false;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
            )
    @NotNull(message = "Account holder must not be null")
    private Set<AccountHolder> accountHolders;

    public Account() {
        // default no args constructor
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public @NotNull(message = "Amount cannot be null") Float getAmount() {
        return amount;
    }

    public void setAmount(@NotNull(message = "Amount cannot be null") Float amount) {
        this.amount = amount;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Set<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(Set<AccountHolder> accountHolder) {
        this.accountHolders = accountHolder;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", amount=" + amount + '\'' +
                ", blocked=" + blocked + '\'' +
                ", accountHolder=" + accountHolders + '\'' +
                ", create=" + getCreate() + '\'' +
                ", lastModified=" + getLastModified() + '\'' +
                ", version=" + getVersion() +
                '}';
    }
}
