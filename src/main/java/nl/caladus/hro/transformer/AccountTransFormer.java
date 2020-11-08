package nl.caladus.hro.transformer;

import nl.caladus.hro.dto.AccountDto;
import nl.caladus.hro.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountTransFormer {

    public static AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setIBAN(account.getIBAN());
        accountDto.setAmount(account.getAmount());
        accountDto.setBlocked(account.isBlocked());
        accountDto.setAccountHolders(account.getAccountHolders());
        return accountDto;
    }
}
