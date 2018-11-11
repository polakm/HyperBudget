package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.text.MessageFormat;

public class AmountIsRequired implements ValidationRule {

    private static String MESSAGE_PATTERN = "Amount of transaction with id \"{0}\" is null. The amount is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if (transaction.getAmount() == null) {
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
