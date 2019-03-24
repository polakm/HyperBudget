package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.money.Money;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import static org.junit.Assert.*;

public class AmountIsRequiredTest {

    @Test
    public void validateTransactionWithoutAmount() {

        //given
        ValidationRule rule = new AmountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithoutAmount());
        } catch (InvalidTransactionException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }

    @Test
    public void validateTransactionWithPositiveAmount() {

        //given
        ValidationRule rule = new AmountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithAmount("USD 99999999999999.99"));
        } catch (InvalidTransactionException e) {
            fail("Method not should throw InvalidAccountException.");
        }

        //then
        return;
    }

    @Test
    public void validateTransactionWithNegativeAmount() {

        //given
        ValidationRule rule = new AmountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithAmount("USD -99999999999999.99"));
        } catch (InvalidTransactionException e) {
            fail("Method not should throw InvalidAccountException.");
        }

        //then
        return;
    }

    @Test
    public void validateTransactionWithZeroAmount() {

        //given
        ValidationRule rule = new AmountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithAmount("USD 0.00"));
        } catch (InvalidTransactionException e) {
            fail("Method should throw InvalidAccountException.");
        }

        //then
        return;
    }

    private Transaction getTransactionWithoutAmount() {
        Transaction transaction = new Transaction();
        transaction.setAmount(null);
        return transaction;
    }


    private Transaction getTransactionWithAmount(String moneyString) {
        Transaction transaction = new Transaction();
        transaction.setAmount(Money.parse(moneyString));
        return transaction;

    }

}