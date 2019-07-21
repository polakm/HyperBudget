package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyIsAcceptedTest {

    @Test
    public void validCurrency() throws InvalidTransactionException {

        //given
        CurrencyIsAccepted currencyValidationRule = CurrencyIsAccepted.of(CurrencyUnit.USD);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(Money.parse("USD 200"));

        //when
        try {
            currencyValidationRule.validate(transaction);

            //then
            assertTrue(true);
        } catch (InvalidTransactionException e) {
            fail();

        }
    }

    ;

    @Test
    public void invalidCurrency() {
        //given
        CurrencyIsAccepted currencyValidationRule = CurrencyIsAccepted.of(CurrencyUnit.USD);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(Money.parse("PLN 200"));

        //when
        try {
            currencyValidationRule.validate(transaction);
            //then
            fail();
        } catch (InvalidTransactionException e) {
            assertTrue(true);

        }
    }

    ;


    @Test
    public void validOfManyAcceptedCurrencies() {
        //given
        CurrencyIsAccepted currencyValidationRule = CurrencyIsAccepted.of(CurrencyUnit.USD, CurrencyUnit.EUR);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(Money.parse("EUR 200"));

        //when
        try {
            currencyValidationRule.validate(transaction);

            //then
            assertTrue(true);
        } catch (InvalidTransactionException e) {
            fail();

        }
    }

    ;

    @Test
    public void invalidOfManyAcceptedCurrencies() {
        //given
        CurrencyIsAccepted currencyValidationRule = CurrencyIsAccepted.of(CurrencyUnit.USD, CurrencyUnit.EUR);
        Transaction transaction = mock(Transaction.class);
        when(transaction.getAmount()).thenReturn(Money.parse("PLN 200"));

        //when
        try {
            currencyValidationRule.validate(transaction);
            //then
            fail();
        } catch (InvalidTransactionException e) {
            assertTrue(true);

        }
    }

    ;


}