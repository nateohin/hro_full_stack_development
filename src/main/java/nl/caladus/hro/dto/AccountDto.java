package nl.caladus.hro.dto;

import nl.caladus.hro.model.AccountHolder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class AccountDto {

    private long id;

    @NotNull(message = "IBAN cannot be null")
    @Size(min = 18, max = 18, message = "IBAN needs to be 18 characters long")
    private String IBAN;

    @NotNull(message = "Amount cannot be null")
    private  Float amount;

    private boolean blocked;

    @NotNull(message = "Account holder must not be null")
    private Set<AccountHolder> accountHolders;

    public AccountDto() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setAccountHolders(Set<AccountHolder> accountHolders) {
        this.accountHolders = accountHolders;
    }

    public Set<AccountHolder> getAccountHolders() {
        return accountHolders;
    }
}
