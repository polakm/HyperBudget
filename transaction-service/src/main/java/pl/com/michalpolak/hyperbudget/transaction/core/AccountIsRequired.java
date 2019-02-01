package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.text.MessageFormat;

class AccountIsRequired implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountIsRequired.class);

    private static final String MESSAGE_PATTERN = "Account ID in transaction with id \"{0}\" is null or empty. The account id is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getAccountId() == null) {

            LOGGER.info("Account ID is null - Transaction ID: {} ", transaction.getId());
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

        if (transaction.getAccountId().isEmpty()) {

            LOGGER.info("Account ID is empty - Transaction ID: {} ", transaction.getId());
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
