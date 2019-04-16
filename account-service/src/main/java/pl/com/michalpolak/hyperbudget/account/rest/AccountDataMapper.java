package pl.com.michalpolak.hyperbudget.account.rest;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class AccountDataMapper {

    AccountData mapToData(Account account) {
        return new AccountData(account.getId(), account.getName());
    }

    Account mapToEntity(AccountData account) {
        if (account.getId() == null) {
            new Account(account.getName());
        }
        return new Account(account.getId(), account.getName());
    }

    Account mapToEntity(String id, AccountData account) {
        return new Account(id, account.getName());
    }

    List<AccountData> mapToDataList(Set<Account> accounts) {
        List<AccountData> list = accounts.stream().map(a -> mapToData(a)).collect(Collectors.toList());
        return Collections.unmodifiableList(list);
    }

}
