package nl.caladus.hro.dto;

import javax.validation.constraints.NotNull;

public class CombinedAccountDto {

    private long id;

    @NotNull
    private AccountDto accountDto1;

    @NotNull
    private  AccountDto accountDto2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountDto getAccountDto1() {
        return accountDto1;
    }

    public void setAccountDto1(AccountDto accountDto1) {
        this.accountDto1 = accountDto1;
    }

    public AccountDto getAccountDto2() {
        return accountDto2;
    }

    public void setAccountDto2(AccountDto accountDto2) {
        this.accountDto2 = accountDto2;
    }
}
