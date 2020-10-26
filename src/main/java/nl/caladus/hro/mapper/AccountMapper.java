package nl.caladus.hro.mapper;

import nl.caladus.hro.dto.AccountDto;
import nl.caladus.hro.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface AccountMapper {

    AccountDto toDto(Account entity);

    Account toEntity(AccountDto accountDto);

    @Named("IBAN")
    static int toAccountNumber(String IBAN) {
        try {
            return Integer.parseInt(IBAN.substring(IBAN.length() - 8));
        } catch (Exception e) {
            return 0;
        }
    }
}
