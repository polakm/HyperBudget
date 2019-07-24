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
        return mapEntityToData(account);
    }

    final AccountData mapEntityToData(Account account) {
        return AccountData.of(account.getId().toString(), account.getName().toString());
    }

    Account mapToEntity(AccountData account) {
        if (account.getId() == null) {
            Account.of(account.getName());
        }
        return Account.of(account.getId(), account.getName());
    }

    Account mapToEntity(String id, AccountData account) {
        return Account.of(id, account.getName());
    }

    List<AccountData> mapToDataList(Set<Account> accounts) {
        List<AccountData> list = accounts.stream().map(a -> mapEntityToData(a)).collect(Collectors.toList());
        return Collections.unmodifiableList(list);
    }

}
