package nl.caladus.hro.service;

import nl.caladus.hro.dto.CombinedAccountDto;
import nl.caladus.hro.mapper.CombinedAccountMapper;
import nl.caladus.hro.model.CombinedAccount;
import nl.caladus.hro.repository.CombinedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CombinedAccountService {

    private final CombinedAccountRepository combinedAccountRepository;
    private final CombinedAccountMapper combinedAccountMapper;

    @Autowired
    public CombinedAccountService(
            CombinedAccountRepository combinedAccountRepository,
            CombinedAccountMapper combinedAccountMapper) {

        this.combinedAccountRepository = combinedAccountRepository;
        this.combinedAccountMapper = combinedAccountMapper;
    }

    @Cacheable(cacheNames = "combinedAccounts", unless = "#result.size() <= 5")
    public CombinedAccountDto createCombinedAccount(CombinedAccount combinedAccount) {
        return combinedAccountMapper.toDto(combinedAccountRepository.save(combinedAccount));
    }

    @Cacheable(cacheNames = "combinedAccounts")
    public CombinedAccount getCombinedAccountByIBAN(String IBAN) {
        return combinedAccountRepository.findByIBAN(IBAN);
    }

    /*
    result list > 5 for testing purposes
     */
    @Cacheable(cacheNames = "combinedAccounts", unless = "#result.size() <= 5")
    public List<CombinedAccountDto> getAccounts() {
        List <CombinedAccountDto> combinedAccountDtos = new ArrayList<>();
        combinedAccountRepository.findAll()
                .forEach(combinedAccount -> combinedAccountDtos
                        .add(combinedAccountMapper.toDto(combinedAccount)));
        return combinedAccountDtos;
    }


    @CacheEvict(value = "combinedAccounts", allEntries = true)
    public void deleteCombinedAccountByIBAN(String iban) {
        combinedAccountRepository.deleteByIBAN(iban);
    }
}
