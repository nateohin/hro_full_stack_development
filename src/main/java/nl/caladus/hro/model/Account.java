package nl.caladus.hro.model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Account implements  Comparable<Account> {

    private String id;

    @NotNull(message = "IBAN cannot be null")
    private String IBAN;

    @NotNull(message = "Amount cannot be null")
    private float amount;

    private boolean blocked = false;

    @NotNull(message = "AccountHolders cannot be null")
    private List<AccountHolder> accountHolders;

    public Account() {
        // default no args constructor
    }

    public Account(String IBAN, float amount, List<AccountHolder> accountHolders) {
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
        this.amount = amount;
    }

    public List<AccountHolder> getAccountHolders() {
        return accountHolders;
    }

    public void setAccountHolders(List<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
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

    @Override
    public int compareTo(Account a) {
        return this.getIBAN().compareTo(a.getIBAN());
    }
}
