package pl.com.michalpolak.hyperbudget.account.rest;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountDataList {

    private List<AccountData> accounts;

    public AccountDataList(Set<Account> accountSet) {

        accounts = new ArrayList<>();

        accountSet.forEach(c -> {
            accounts.add(new AccountData(c));
        });

    }


    public List<AccountData> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountData> accounts) {
        this.accounts = accounts;
    }

    public List<AccountData> asList() {
        return accounts;
    }
}
