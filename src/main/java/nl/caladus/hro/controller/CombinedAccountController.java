package nl.caladus.hro.controller;

import io.swagger.annotations.ApiOperation;
import nl.caladus.hro.dto.CombinedAccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

public interface CombinedAccountController {

    @ApiOperation(value = "Create a combined account")
    @PostMapping
    ResponseEntity<?> createCombinedAccount(@Valid @RequestBody CombinedAccountDto combinedAccountDto);

    @ApiOperation(value = "Get multiple cobined accounts")
    @GetMapping
    ResponseEntity<List<CombinedAccountDto>> getCombinedAccountsAccounts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize);

    @ApiOperation(value = "Get a combined account")
    @GetMapping("/{IBAN}")
    ResponseEntity<CombinedAccountDto> getCombinedAccount(@PathVariable String IBAN);


    @ApiOperation(value = "Delete a combined")
    @DeleteMapping("/{IBAN}")
    HttpStatus deleteCombinedAccount(@PathVariable String IBAN);
}
