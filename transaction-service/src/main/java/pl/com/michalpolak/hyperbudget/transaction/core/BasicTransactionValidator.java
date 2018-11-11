package pl.com.michalpolak.hyperbudget.transaction.core;

import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BasicTransactionValidator implements TransactionValidator {

    private List<ValidationRule> validationRules;

    BasicTransactionValidator(List<ValidationRule> validationRules) {

        this.validationRules = validationRules;
    }

    BasicTransactionValidator(ValidationRule... validationRules) {

        this.validationRules = new ArrayList(Arrays.asList(validationRules));
    }

    BasicTransactionValidator() {
        this.validationRules = new ArrayList<>();
    }

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        for (ValidationRule validationRule : this.validationRules) {

            validationRule.validate(transaction);
        }
    }

}
