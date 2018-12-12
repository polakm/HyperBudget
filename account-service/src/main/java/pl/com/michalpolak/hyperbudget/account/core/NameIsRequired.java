package pl.com.michalpolak.hyperbudget.account.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

import java.text.MessageFormat;

class NameIsRequired implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(NameIsRequired.class);

    private static final String MESSAGE_PATTERN = "Account ID in account with id \"{0}\" is null or empty. The account id is required value.";

    @Override
    public void validate(Account account) throws InvalidAccountException {

        if (account.getName() == null) {

            LOGGER.info("Name is null - Account ID: {} ", account.getId());
            throw new InvalidAccountException(MessageFormat.format(MESSAGE_PATTERN, account.getId()));
        }

        if (account.getName().isEmpty()) {

            LOGGER.info("Name is empty - Account ID: {} ", account.getId());
            throw new InvalidAccountException(MessageFormat.format(MESSAGE_PATTERN, account.getId()));
        }

    }
}
