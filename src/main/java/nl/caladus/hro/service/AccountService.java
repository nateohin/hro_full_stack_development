package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.model.AccountHolder;
import nl.caladus.hro.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account) { ;
        accountRepository.addAccount(account);
    }

    public Account getAccount(String IBAN) {
        return accountRepository.getAccount(IBAN);
    }

    public List<Account> getAccounts() {
        return accountRepository.getAccounts();
    }

    public List<AccountHolder> getAccountHolders(String IBAN) {
        return accountRepository.getAccountHolders(IBAN);
    }

    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    public void deleteAccount(String IBAN) {
        accountRepository.deleteAccount(IBAN);
    }

    public List<Account> getAccounts(Integer pageNo, Integer pageSize) {
        return accountRepository.getAccounts(pageNo,pageSize);
    }
}