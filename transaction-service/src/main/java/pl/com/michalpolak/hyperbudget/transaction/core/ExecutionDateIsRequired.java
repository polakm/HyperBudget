package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.text.MessageFormat;

public class ExecutionDateIsRequired implements ValidationRule {

    private static String MESSAGE_PATTERN = "Execution date of transaction with id \"{0}\" is null. The execution date is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getExecutionDate() == null) {
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
