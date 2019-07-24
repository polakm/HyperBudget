package pl.com.michalpolak.hyperbudget.account.core.api;


import java.util.Set;

public interface AccountService {

    Account addAccount(Account account) throws InvalidAccountException;

    void removeAccount(AccountId id) throws AccountNotFoundException;

    Account getAccount(AccountId id) throws AccountNotFoundException;

    Set<Account> allAccounts();

    Account updateAccount(Account account) throws AccountNotFoundException;
}
