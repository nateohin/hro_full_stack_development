package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void updateAccount(Account account) {
        accountRepository.getAccounts().computeIfPresent(account.getIBAN(), (key, value) -> {
            if (account.getAmount() > -100000000) {
                value.setAmount(account.getAmount() + value.getAmount());
            }
            if (account.getAccountHolders() != null) {
                account.getAccountHolders().forEach(accountHolder -> {
                    value.getAccountHolders().forEach(accountHolder1 -> {
                        if (accountHolder.equals(accountHolder1)) {
                            // TODO
                            value.getAccountHolders().remove(accountHolder1);
                        }
                    });
                });
            }
            if (account.getAccountHolders() != null) {
                value.getAccountHolders().addAll(account.getAccountHolders());
            }
            if (account.getAccountHolders() != null && account.getAccountHolders().isEmpty()) {
                value.setAccountHolders(account.getAccountHolders());
            }
            return value;
        });
    }

    public void deleteAccount(String IBAN) {
        accountRepository.getAccounts().remove(IBAN);
    }
}