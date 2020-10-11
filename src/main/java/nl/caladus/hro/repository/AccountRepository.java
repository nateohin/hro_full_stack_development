package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByIBAN(String IBAN);

    void deleteByIBAN(String IBAN);
}
