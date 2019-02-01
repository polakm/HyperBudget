package pl.com.michalpolak.hyperbudget.account.core;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

public interface ValidationRule {
    void validate(Account account) throws InvalidAccountException;
}
