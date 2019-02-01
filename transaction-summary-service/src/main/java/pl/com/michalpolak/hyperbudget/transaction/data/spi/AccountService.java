package pl.com.michalpolak.hyperbudget.transaction.data.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;

public interface AccountService {

    Account getAccount(String accountId);
}
