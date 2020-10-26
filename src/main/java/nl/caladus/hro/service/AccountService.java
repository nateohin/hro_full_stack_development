package nl.caladus.hro.service;

import nl.caladus.hro.dto.AccountDto;
import nl.caladus.hro.mapper.AccountMapper;
import nl.caladus.hro.model.Account;
import nl.caladus.hro.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountDto createAccount(Account account) {
        return accountMapper.toDto(accountRepository.save(account));
    }

    @Cacheable(cacheNames = "accounts")
    public Account getAccountByIBAN(String IBAN) {
        return accountRepository.findByIBAN(IBAN);
    }

    /*
    result list > 5 for testing purposes
     */
    @Cacheable(cacheNames = "accounts", unless = "#result.size() <= 5")
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @CachePut(value = "accounts")
    public void updateAccount(Account oldAccount, AccountDto account) {
        if (account.getAmount() != null ) {
            oldAccount.setAmount(oldAccount.getAmount() + account.getAmount());
        }
        if (account.getAccountHolders() != null) {
            oldAccount.setAccountHolders(account.getAccountHolders());
        }
        if (account.isBlocked() != null) {
            oldAccount.setBlocked(account.isBlocked());
        }
        accountRepository.save(oldAccount);
    }

    @CacheEvict(value = "accounts", allEntries = true)
    public void deleteAccountByIBAN(String IBAN) {
        accountRepository.deleteByIBAN(IBAN);
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