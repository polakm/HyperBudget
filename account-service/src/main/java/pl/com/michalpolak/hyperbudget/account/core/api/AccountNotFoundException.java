package pl.com.michalpolak.hyperbudget.account.core.api;

import java.text.MessageFormat;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String id) {
        super(MessageFormat.format("Account with id \"{0}\" not found", id));
    }

}
