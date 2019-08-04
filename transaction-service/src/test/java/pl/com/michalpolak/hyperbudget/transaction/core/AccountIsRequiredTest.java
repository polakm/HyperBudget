package pl.com.michalpolak.hyperbudget.transaction.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.AccountId;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import java.util.UUID;

import static org.junit.Assert.fail;

public class AccountIsRequiredTest {

    @Test
    public void validateTransactionWithoutAccount() {

        //given
        ValidationRule rule = new AccountIsRequired();

        //when
        try {
            rule.validate(getTransactionWithoutAccountId());
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


    private Transaction getTransactionWithoutAccountId() {

        return Transaction.builder().forAccount(null).build();
    }


    private Transaction getTransactionWithAccountId(String accountID) {

        return Transaction.builder().forAccount(AccountId.fromString(accountID)).build();

    }

}