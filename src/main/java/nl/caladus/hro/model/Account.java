package nl.caladus.hro.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Account {

    private String id;

    @NotNull(message = "IBAN cannot be null")
    private String IBAN;

    @NotNull(message = "Amount cannot be null")
    private float amount;

    @NotNull(message = "AccountHolders cannot be null")
    private List<AccountHolder> accountHolders;

    public Account() {
        // default no args constructor
    }

    public Account(
                   @NotNull(message = "IBAN cannot be null") String IBAN,
                   @NotNull(message = "Amount cannot be null") float amount,
                   @NotNull(message = "AccountHolders cannot be null") List<AccountHolder> accountHolders) {
        this.IBAN = IBAN;
        this.amount = amount;
        this.accountHolders = accountHolders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.amount = this.amount + amount;
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", IBAN='" + IBAN + '\'' +
                ", amount=" + amount +
                ", accountHolders=" + accountHolders +
                '}';
    }
}
