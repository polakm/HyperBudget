package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.text.MessageFormat;

class AmountIsRequired implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmountIsRequired.class);

    private static final String MESSAGE_PATTERN = "Amount of transaction with id \"{0}\" is null. The amount is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getAmount() == null) {
            LOGGER.info("Transaction amount is null - Transaction ID: {} ", transaction.getId());
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
