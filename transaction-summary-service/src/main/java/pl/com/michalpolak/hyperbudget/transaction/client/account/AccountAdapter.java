package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;

@Component
public class AccountAdapter implements AccountService {

    private AccountServiceClient client;

    @Autowired
    public AccountAdapter(AccountServiceClient client) {
        this.client = client;
    }

    @Override
    public Account getAccount(String id) {
        return new AccountDataAdapter(client.getAccount(id));
    }
}
