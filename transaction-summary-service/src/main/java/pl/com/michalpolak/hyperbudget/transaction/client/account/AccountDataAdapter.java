package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;

class AccountDataAdapter extends Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDataAdapter.class);

    private AccountDataAdapter(AccountData accountData) {
        super(accountData.getId(), accountData.getName());
    }

    static AccountDataAdapter of(AccountData accountData) {
        return new AccountDataAdapter(accountData);
    }

}
