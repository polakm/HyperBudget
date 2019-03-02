package pl.com.michalpolak.hyperbudget.transaction.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.UUID;

import static org.junit.Assert.*;

public class AccountIsRequiredTest {

    @Test
    public void validateTransactionWithoutAccount() {

        //given
        ValidationRule rule = new AccountIsRequired();

        //when
        try {
            rule.validate(getTranasctionWithoutAccountId());
        } catch (InvalidTransactionException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }


    @Test
    public void validateTransactionWithEmptyAccountId() {

        //given
        ValidationRule rule = new AccountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithAccountId(""));
        } catch (InvalidTransactionException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }


    @Test
    public void validateTransactionWithAccount() {

        //given
        ValidationRule rule = new AccountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithAccountId(UUID.randomUUID().toString()));
        } catch (InvalidTransactionException e) {
            fail("Method not should throw InvalidAccountException.");
        }

        //then
        return;
    }


    private Transaction getTranasctionWithoutAccountId() {
        Transaction transaction = new Transaction();
        transaction.setAccountId(null);
        return transaction;
    }


    private Transaction getTransactionWithAccountId(String accountID) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountID);
        return transaction;

    }

}