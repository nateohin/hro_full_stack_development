package nl.caladus.hro.controller;

import nl.caladus.hro.aop.LogExecutionTime;
import nl.caladus.hro.dto.AccountDto;
import nl.caladus.hro.mapper.AccountMapper;
import nl.caladus.hro.model.Account;
import nl.caladus.hro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class AccountControllerImpl extends BaseController implements AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Autowired
    public AccountControllerImpl(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Override
    @LogExecutionTime
    public ResponseEntity<?> createAccount(AccountDto accountDto) {

        Account account=  accountMapper.toEntity(accountDto);
        account.setAccountNumber(AccountMapper.toAccountNumber(accountDto.getIBAN()));
        account = accountService.createAccount(account);

        return ResponseEntity.ok(account);
    }

    @Override
    @LogExecutionTime
    public ResponseEntity<List<Account>> getAccounts(Integer pageNo, Integer pageSize) {
//        List<Account> accounts = accountService.getAccounts(pageNo, pageSize);
        List<Account> accounts = accountService.getAccounts();
        if (accounts == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    @Override
    @LogExecutionTime
    public ResponseEntity<AccountDto> getAccount(String IBAN) {

        Account account = accountService.getAccountByIBAN(IBAN);
        if (account == null) {
            return ResponseEntity.noContent().build();
        }
        AccountDto accountDto = accountMapper.toDto(account);
            return ResponseEntity.ok(accountDto);
    }

    @Override
    @LogExecutionTime
    public HttpStatus updateAccount(AccountDto accountDto) {
        Account account1 = accountService.getAccountByIBAN(accountDto.getIBAN());
        if (account1 == null) {
            return HttpStatus.NO_CONTENT;
        }
        accountService.updateAccount(account1, accountDto);
        return HttpStatus.ACCEPTED;
    }

    @Override
    @LogExecutionTime
    public HttpStatus deleteAccount(String IBAN) {
        accountService.deleteAccountByIBAN(IBAN);
        return HttpStatus.ACCEPTED;
        }
    }