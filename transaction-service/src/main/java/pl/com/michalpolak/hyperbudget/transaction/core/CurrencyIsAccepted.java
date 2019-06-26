package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.CurrencyUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CurrencyIsAccepted implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionDateIsRequired.class);
    private static String MESSAGE_PATTERN = "Currency \"{0}\" is not accepted. The accepted currencies is only {1}.";
    private final List<CurrencyUnit> acceptableCurrencies;

    CurrencyIsAccepted(CurrencyUnit... currencyUnits) {
        this.acceptableCurrencies = new ArrayList<>(Arrays.asList(currencyUnits));
    }

    @Override
    public void validate(Transaction transaction) throws InvalidTransactionException {

        CurrencyUnit currency = transaction.getAmount().getCurrencyUnit();

        if (!this.acceptableCurrencies.contains(currency)) {
            throw new InvalidTransactionException(MessageFormat.format(MESSAGE_PATTERN, currency.getCode(), acceptableCurrencies));
        }
    }
}
