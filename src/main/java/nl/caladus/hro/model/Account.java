package nl.caladus.hro.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Account extends BaseEntity {

    @NotNull(message = "IBAN cannot be null")
    @Size(min = 18, max = 18, message = "IBAN needs to be 18 characters")
    @Column(unique=true)
    private String IBAN;

    @Min(value = 8,  message = "Account number needs to be 8 characters")
    @NotNull
    private int accountNumber;

    @NotNull(message = "Amount cannot be null")
    private  Float amount;

    private boolean blocked = false;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @NotNull(message = "Account holder must not be null")
    private Set<AccountHolder> accountHolders;

    public Account() {}

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getIBAN() {
        return IBAN;
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

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "IBAN='" + IBAN + '\'' +
                ", amount=" + amount + '\'' +
                ", accountNumber=" + accountNumber + '\'' +
                ", blocked=" + blocked + '\'' +
                ", accountHolder=" + accountHolders + '\'' +
                ", create=" + getCreate() + '\'' +
                ", lastModified=" + getLastModified() + '\'' +
                ", version=" + getVersion() +
                '}';
    }
}
