package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.repository.AccountRepository;
import nl.caladus.hro.utils.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account) { ;
        accountRepository.getAccounts().put(account.getIBAN(), account);
    }

    public Account getAccount(String IBAN) {
        return accountRepository.getAccounts().get(IBAN);
    }

    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public List<Account> getAccounts() {
        return new ArrayList<>(accountRepository.getAccounts().values());
    }

    public void updateAccount(Account account) {
        accountRepository.getAccounts().computeIfPresent(account.getIBAN(), (key, value) -> {
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

    public void deleteAccount(String IBAN) {
        accountRepository.getAccounts().remove(IBAN);
    }

    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public List<Account> getAccounts(Integer pageNo, Integer pageSize) {
        List<Account> accounts = new ArrayList<>(accountRepository.getAccounts().values());
        Pageable<Account> accountPageable = new Pageable<>(accounts);
        accountPageable.setPage(pageNo);
        accountPageable.setPageSize(pageSize);
        return accountPageable.getList();
    }
}