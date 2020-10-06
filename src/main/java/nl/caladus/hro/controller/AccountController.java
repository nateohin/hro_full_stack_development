package nl.caladus.hro.controller;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity addAccount(@Valid @RequestBody Account account) {
        try {
            account.setId(UUID.randomUUID().toString());
            accountService.addAccount(account);
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }
    }

    @GetMapping
    public ResponseEntity getAccounts(@RequestParam(defaultValue = "0") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            List<Account> accounts = accountService.getAccounts(pageNo, pageSize);
            if (accounts == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }
    }

    @GetMapping("/{IBAN}")
    public ResponseEntity getAccount(@PathVariable String IBAN) {
        try {
            Account account = accountService.getAccount(IBAN);
            if (account == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }
    }

    @PutMapping
    public HttpStatus updateAccount(@RequestBody Account account) {
        try {
            Account account1 = accountService.getAccount(account.getIBAN());
            if (account1 == null) {
                return HttpStatus.NO_CONTENT;
            }
            accountService.updateAccount(account);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request").getStatusCode();
        }
    }

    @DeleteMapping("/{IBAN}")
    public HttpStatus deleteAccount(@PathVariable String IBAN) {
        try {
            Account account1 = accountService.getAccount(IBAN);
            if (account1 == null) {
                return HttpStatus.NO_CONTENT;
            }
            accountService.deleteAccount(IBAN);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request").getStatusCode();
        }
    }
}
