package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.text.MessageFormat;

public class AccountIsRequired implements ValidationRule {

    private static String MESSAGE_PATTERN = "Account ID in transaction with id \"{0}\" is null or empty. The account id is required value.";

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        if(transaction.getAccountId() == null){
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

        if(transaction.getAccountId().isEmpty()){
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, transaction.getId()));
        }

    }
}
