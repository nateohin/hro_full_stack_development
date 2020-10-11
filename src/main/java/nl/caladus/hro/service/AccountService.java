package nl.caladus.hro.service;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.repository.AccountHolderRepository;
import nl.caladus.hro.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountHolderRepository accountHolderRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountHolderRepository accountHolderRepository) {
        this.accountRepository = accountRepository;
        this.accountHolderRepository = accountHolderRepository;
    }

    public void addAccount(Account account) { ;
        accountRepository.save(account);
    }

    public Account getAccountByIBAN(String IBAN) {
        return accountRepository.findByIBAN(IBAN);
    }

    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccountByIBAN(String IBAN) {
        accountRepository.deleteByIBAN(IBAN);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }



    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 20")
    public List<Account> getAccounts(Integer pageNo, Integer pageSize) {
//        List<Account> accounts = new ArrayList<>(accountRepository.getAccounts().values());
//        Pageable<Account> accountPageable = new Pageable<>(accounts);
//        accountPageable.setPage(pageNo);
//        accountPageable.setPageSize(pageSize);
//        return accountPageable.getList();
        return null;
    }
}