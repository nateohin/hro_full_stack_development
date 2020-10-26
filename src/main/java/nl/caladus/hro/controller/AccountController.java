package nl.caladus.hro.controller;

import io.swagger.annotations.ApiOperation;
import nl.caladus.hro.dto.AccountDto;
import nl.caladus.hro.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/account")
public interface AccountController {

    @ApiOperation(value = "Create an account")
    @PostMapping
    ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto accountDto);

    @ApiOperation(value = "Get multiple accounts")
    @GetMapping
    ResponseEntity<List<Account>> getAccounts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize);

    @ApiOperation(value = "Get an account")
    @GetMapping("/{IBAN}")
    ResponseEntity<AccountDto> getAccount(@PathVariable String IBAN);

    @ApiOperation(value = "Update an account")
    @PutMapping
    HttpStatus updateAccount(@Valid  @RequestBody AccountDto accountDto);

    @ApiOperation(value = "Delete an account")
    @DeleteMapping("/{IBAN}")
    HttpStatus deleteAccount(@PathVariable String IBAN);

}
