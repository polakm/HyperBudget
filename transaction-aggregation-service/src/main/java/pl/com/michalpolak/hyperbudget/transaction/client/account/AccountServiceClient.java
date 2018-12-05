package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Account;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.AccountService;

import java.util.HashMap;

@Component
class AccountServiceClient implements AccountService {


  private  HashMap<String, Account> categories;

    AccountServiceClient(){
        this.categories = new HashMap<>();
        categories.put( "aaaaaa", new Account( "aaaaaa", "Bank"));
        categories.put( "bbbbbb", new Account( "bbbbbb", "Wallet"));
        categories.put( "cccccc", new Account( "cccccc", "Company Account\""));
        categories.put( "dddddd", new Account( "dddddd", "Piggybank"));


    }

    public Account getAccount(String id) {
         return  this.categories.get(id);
    }
}
