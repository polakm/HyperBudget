package pl.com.michalpolak.hyperbudget.account.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.spi.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class DataLayerConfiguration {

    @Bean
    public static AccountRepository categoryRepositoryBean() {

        String id;
        Map<String, Account> initialData = new HashMap<>();
        initialData.put(id = UUID.randomUUID().toString(), new Account(id, "Bank"));
        initialData.put(id = UUID.randomUUID().toString(), new Account(id, "Wallet"));
        initialData.put(id = UUID.randomUUID().toString(), new Account(id, "Piggybank"));
        return new InMemoryAccountRepository(initialData);
    }

}
