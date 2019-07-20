package pl.com.michalpolak.hyperbudget.account.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.AccountNotFoundException;
import pl.com.michalpolak.hyperbudget.account.core.api.AccountService;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

import java.util.Set;

import static org.junit.Assert.*;

public class BasicAccountServiceTest {

    @Test
    public void addAccount() throws AccountNotFoundException, InvalidAccountException {

        //given

        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());
        Account account = getExampleAccount("example-account-name");

        //when
        accountService.addAccount(account);
        Account resultAccount = accountService.getAccount(account.getId());

        //then
        assertNotNull(resultAccount);

    }

    @Test
    public void accountNotFoundException() {

        //given
        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());

        //when
        try {
            accountService.getAccount("non-exist-account-id");
        } catch (AccountNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw AccountNotFoundException.");
    }

    @Test
    public void invalidAccountException() {

        //given
        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());
        Account account = getExampleAccount(null);

        //when
        try {
            accountService.addAccount(account);
        } catch (InvalidAccountException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }


    @Test
    public void updateAccount() throws AccountNotFoundException, InvalidAccountException {

        //given
        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());

        Account account = getExampleAccount("example-account-name");
        accountService.addAccount(account);

        Account updatedAccount =  new Account(account.getId(),"updated-title");

        //when
        accountService.updateAccount(updatedAccount);
        Account resultAccount = accountService.getAccount(account.getId());

        //then
        assertEquals("updated-title", resultAccount.getName());

    }

    @Test
    public void removeAccount() throws AccountNotFoundException, InvalidAccountException {

        //given
        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());
        Account account = getExampleAccount("example-account-name");

        //when
        accountService.addAccount(account);
        accountService.removeAccount(account.getId());

        //when
        try {
            accountService.getAccount(account.getId());
        } catch (AccountNotFoundException e) {
            return;
        }

        //then
        fail("After remove account, method getAccount should throw AccountNotFoundException.");

    }


    @Test
    public void allAccounts() throws InvalidAccountException {

        //given
        AccountService accountService = AccountServiceConfiguration.createAccountService(new InMemoryAccountRepository());
        Account account1 = getExampleAccount("example-account-name-1");
        Account account2 = getExampleAccount("example-account-name-2");
        Account account3 = getExampleAccount("example-account-name-3");

        //when
        accountService.addAccount(account1);
        accountService.addAccount(account2);
        accountService.addAccount(account3);
        Set<Account> accounts = accountService.allAccounts();

        //then
        assertEquals(3, accounts.size());
    }



    private Account getExampleAccount(String name) {
        Account account = new Account(name);
        return account;
    }


}