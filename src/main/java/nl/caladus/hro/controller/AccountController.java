package nl.caladus.hro.controller;

import nl.caladus.hro.model.Account;
import nl.caladus.hro.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private AccountService accountService;

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

    @GetMapping("/{IBAN}")
    public ResponseEntity getAccount(@PathVariable String IBAN) {

        try {
            Account account = accountService.getAccount(IBAN);
            if (account == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(accountService.getAccount(IBAN));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request");
        }
    }

    @PutMapping
    public HttpStatus updateAccount(@RequestBody Account account) {
        try {
            accountService.updateAccount(account);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request").getStatusCode();
        }
    }

    @DeleteMapping("/{IBAN}")
    public HttpStatus deleteAccount(@PathVariable String IBAN) {
        try {
            accountService.deleteAccount(IBAN);
            return HttpStatus.ACCEPTED;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("failed to process request").getStatusCode();
        }
    }
}
