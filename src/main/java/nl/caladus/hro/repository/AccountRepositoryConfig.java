package nl.caladus.hro.repository;

import nl.caladus.hro.model.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AccountRepositoryConfig {

    @Bean
    @Qualifier("accounts")
    public Map<String, Account> accounts() {
        return new HashMap<>();
    }
}
