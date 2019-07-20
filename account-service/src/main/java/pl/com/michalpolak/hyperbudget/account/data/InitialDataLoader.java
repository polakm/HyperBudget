package pl.com.michalpolak.hyperbudget.account.data;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface InitialDataLoader {

   List<Account> loadAsList() throws IOException;

   Map<String, Account> loadAsMap() throws IOException;

}
