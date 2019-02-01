package pl.com.michalpolak.hyperbudget.account.core.api;


import java.util.Set;

public interface AccountService {

    Account addAccount(Account account) throws InvalidAccountException;

    void removeAccount(String id) throws AccountNotFoundException;

    Account getAccount(String id) throws AccountNotFoundException;

    Set<Account> allAccounts();

    Account updateAccount(Account account) throws AccountNotFoundException;
}
