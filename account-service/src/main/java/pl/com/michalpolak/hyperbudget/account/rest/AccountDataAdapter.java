package pl.com.michalpolak.hyperbudget.account.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;

class AccountDataAdapter extends Account {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDataAdapter.class);

    AccountDataAdapter(AccountData accountData) {
        super();
        setName(accountData.getName());
    }

    public AccountDataAdapter(String id, AccountData accountData) {
        this(accountData);
        setId(id);
    }


}
