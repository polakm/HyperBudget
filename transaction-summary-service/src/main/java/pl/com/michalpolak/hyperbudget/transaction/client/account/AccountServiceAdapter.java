package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.AccountService;

@Component
final class AccountServiceAdapter implements AccountService {

    private final AccountServiceClient client;

    @Autowired
    AccountServiceAdapter(AccountServiceClient client) {
        this.client = client;
    }

    @Override
    public Account getAccount(String id) {
        return AccountDataAdapter.of(client.getAccount(id));
    }
}
