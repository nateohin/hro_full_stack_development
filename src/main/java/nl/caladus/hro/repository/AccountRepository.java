package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AccountRepository {

    private final Map<String, Account> accounts;

    public AccountRepository(@Qualifier("accounts") Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }
}
