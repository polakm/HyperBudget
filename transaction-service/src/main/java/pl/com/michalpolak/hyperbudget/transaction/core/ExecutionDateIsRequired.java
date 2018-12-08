package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.text.MessageFormat;

class ExecutionDateIsRequired implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionDateIsRequired.class);

    private static String MESSAGE_PATTERN = "Execution date of transaction with id \"{0}\" is null. The execution date is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getExecutionDate() == null) {

            LOGGER.info("Execution date is null - Transaction ID: {} ", transaction.getId());
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
