package nl.caladus.hro.model;

import javax.validation.constraints.NotNull;

public class CombinedAccount extends BaseEntity {

    @NotNull
    Account account1;

    @NotNull
    Account account2;

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
}
