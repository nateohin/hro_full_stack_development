package nl.caladus.hro.controller;

import nl.caladus.hro.aop.LogExecutionTime;
import nl.caladus.hro.dto.CombinedAccountDto;
import nl.caladus.hro.mapper.AccountMapper;
import nl.caladus.hro.mapper.CombinedAccountMapper;
import nl.caladus.hro.model.CombinedAccount;
import nl.caladus.hro.service.CombinedAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class CombinedAccountControllerImpl extends BaseController implements CombinedAccountController {

    private final CombinedAccountService combinedAccountService;
    private final CombinedAccountMapper combinedAccountMapper;


    @Autowired
    public CombinedAccountControllerImpl(
            CombinedAccountService combinedAccountService,
            CombinedAccountMapper combinedAccountMapper) {
        this.combinedAccountService = combinedAccountService;
        this.combinedAccountMapper = combinedAccountMapper;
    }



    @Override
    @LogExecutionTime
    public ResponseEntity<?> createCombinedAccount(CombinedAccountDto combinedAccountDto) {
        CombinedAccount combinedAccount =  combinedAccountMapper.toEntity(combinedAccountDto);
        combinedAccount.getAccount1().setAccountNumber(AccountMapper.toAccountNumber(combinedAccount.getAccount1().getIBAN()));
        combinedAccount.getAccount2().setAccountNumber(AccountMapper.toAccountNumber(combinedAccount.getAccount2().getIBAN()));
        return ResponseEntity.ok(combinedAccountService.createCombinedAccount(combinedAccount));
    }

    @Override
    @LogExecutionTime
    public ResponseEntity<List<CombinedAccountDto>> getCombinedAccountsAccounts(Integer pageNo, Integer pageSize) {
        List<CombinedAccountDto> combinedAccountDtos = combinedAccountService.getAccounts();
        if (combinedAccountDtos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(combinedAccountDtos);
    }

    @Override
    @LogExecutionTime
    public ResponseEntity<CombinedAccountDto> getCombinedAccount(String IBAN) {
        CombinedAccount combinedAccount = combinedAccountService.getCombinedAccountByIBAN(IBAN);
        if (combinedAccount == null) {
            return ResponseEntity.noContent().build();
        }
        CombinedAccountDto combinedAccountDto = combinedAccountMapper.toDto(combinedAccount);
        return ResponseEntity.ok(combinedAccountDto);
    }

    @Override
    @LogExecutionTime
    public HttpStatus deleteCombinedAccount(String IBAN) {
        combinedAccountService.deleteCombinedAccountByIBAN(IBAN);
        return HttpStatus.ACCEPTED;
    }
}
