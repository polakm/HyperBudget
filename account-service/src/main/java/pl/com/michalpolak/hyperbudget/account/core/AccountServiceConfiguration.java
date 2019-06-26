package pl.com.michalpolak.hyperbudget.account.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.account.core.api.AccountService;
import pl.com.michalpolak.hyperbudget.account.core.spi.AccountRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@ComponentScan("pl.com.michalpolak")
class AccountServiceConfiguration {

    @Bean
    public static AccountValidator accountValdiatorBean() {
        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new NameIsRequired());
        return new BasicAccountValidator(Collections.unmodifiableList(rules));
    }

    @Bean
    @Autowired
    public static AccountService accountServiceBean(AccountRepository accountRepository, AccountValidator accountValidator) {
        return new BasicAccountService(accountRepository, accountValidator);
    }

    public static AccountService createAccountService(AccountRepository accountRepository) {
        AccountValidator accountValidator =  accountValdiatorBean();
        return new BasicAccountService(accountRepository, accountValidator);
    }

}
