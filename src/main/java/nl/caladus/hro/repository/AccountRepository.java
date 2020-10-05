package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import nl.caladus.hro.utils.Pageable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


@Repository
public class AccountRepository {

    private final Map<String, Account> accounts;

    public AccountRepository(@Qualifier("accounts") Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public ArrayList<Account> getAccounts() {
        return new ArrayList<>(accounts.values());
    }

    public Account getAccount(String IBAN) {
        return accounts.get(IBAN);
    }

    public List<AccountHolder> getAccountHolders(String IBAN) {
        return accounts.get(IBAN).getAccountHolders();
    }

    public void addAccount(Account account) {
        accounts.put(account.getIBAN(), account);
    }

    public void updateAccount(Account account) {
        accounts.computeIfPresent(account.getIBAN(), (key, value) -> {
            if (account.getAmount() > -100000000) {
                value.setAmount(account.getAmount() + value.getAmount());
            }
            if (account.getAccountHolders() != null) {
                mutateAccountHolders(account, value);
            }
            if (account.isBlocked() != null) {
                value.setBlocked(account.isBlocked());
            }
            return value;
        });
    }

    public void deleteAccount(String IBAN) {
        accounts.remove(IBAN);
    }


    private void mutateAccountHolders(Account account, Account value) {
        AtomicBoolean isAlreadyInList = new AtomicBoolean(false);
        account.getAccountHolders().forEach(accountHolder -> {
            value.getAccountHolders().forEach(accountHolder1 -> {
                if (accountHolder.getFirstName().equals(accountHolder1.getFirstName()) &&
                        accountHolder.getLastName().equals(accountHolder1.getLastName())) {
                    value.getAccountHolders().remove(accountHolder1);
                    isAlreadyInList.set(true);
                }
            });
            if (!isAlreadyInList.get()) {
                value.getAccountHolders().addAll(value.getAccountHolders());
            }
        });
    }

    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public List<Account> getAccounts(Integer pageNo, Integer pageSize) {
        Pageable<Account> accountPageable = new Pageable<>(getAccounts());
        accountPageable.setPage(pageNo);
        accountPageable.setPageSize(pageSize);
        return accountPageable.getList();
    }
}
