package pl.com.michalpolak.hyperbudget.account.core;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

interface AccountValidator {

    void validate(Account account) throws InvalidAccountException;
}
