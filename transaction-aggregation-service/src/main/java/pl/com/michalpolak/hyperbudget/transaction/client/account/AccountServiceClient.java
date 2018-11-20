package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;

@Component
class AccountServiceClient implements AccountService {

    public Account getAccount(String id) {
        return null;
    }
}
