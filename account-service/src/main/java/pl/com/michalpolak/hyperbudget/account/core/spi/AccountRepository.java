package pl.com.michalpolak.hyperbudget.account.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;

import java.util.Set;

@Repository
public interface AccountRepository {

    Account save(Account account);

    Set<Account> getAll();

    void remove(String id);

    Account findById(String id);

    Account update(Account account);
}
