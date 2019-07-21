package pl.com.michalpolak.hyperbudget.transaction.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class BasicTransactionValidator implements TransactionValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicTransactionValidator.class);

    private final List<ValidationRule> validationRules;

    private BasicTransactionValidator(List<ValidationRule> validationRules) {

        this.validationRules = Collections.unmodifiableList(validationRules);
    }

    static TransactionValidator of(List<ValidationRule> rules) {
        return new BasicTransactionValidator(rules);
    }

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        LOGGER.info("Validate transaction - Transaction ID: {}", transaction.getId());
        for (ValidationRule validationRule : this.validationRules) {

            validationRule.validate(transaction);
        }
        LOGGER.info("Transaction is valid - Transaction ID: {}", transaction.getId());
    }

}
