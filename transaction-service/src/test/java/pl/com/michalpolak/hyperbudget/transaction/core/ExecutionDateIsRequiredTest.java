package pl.com.michalpolak.hyperbudget.transaction.core;

import org.joda.time.DateTime;
import org.junit.Test;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;

import static org.junit.Assert.fail;

public class ExecutionDateIsRequiredTest {

    @Test
    public void validateTransactionWithoutExecutionDate() {

        //given
        ValidationRule rule = new ExecutionDateIsRequired();

        //when
        try {
            rule.validate(getTransactionWithoutExecutionDate());
        } catch (InvalidTransactionException e) {
            return;
        }

        //then
        fail("Method should throw InvalidExecutionDateException.");
    }


    @Test
    public void validateTransactionWithExecutionDateNow() {

        //given
        ValidationRule rule = new ExecutionDateIsRequired();

        //when
        try {
            rule.validate(getTransactionWithExecutionDate(DateTime.now()));
        } catch (InvalidTransactionException e) {
            fail("Method should not throw InvalidExecutionDateException.");
        }

        //then
        return;
    }


    @Test
    public void validateTransactionWithExecutionDateInPast() {

        //given
        ValidationRule rule = new ExecutionDateIsRequired();

        //when
        try {
            rule.validate(getTransactionWithExecutionDate("2003-03-23"));
        } catch (InvalidTransactionException e) {
            fail("Method not should throw InvalidExecutionDateException.");
        }

        //then
        return;
    }


    @Test
    public void validateTransactionWithExecutionDateFuture() {

        //given
        ValidationRule rule = new ExecutionDateIsRequired();

        //when
        try {
            rule.validate(getTransactionWithExecutionDate("2030-03-23"));
        } catch (InvalidTransactionException e) {
            fail("Method not should throw InvalidExecutionDateException.");
        }

        //then
        return;
    }


    private Transaction getTransactionWithoutExecutionDate() {
        return Transaction.builder().onExecutionDate(null).build();
    }


    private Transaction getTransactionWithExecutionDate(String dateString) {
        return Transaction.builder().onExecutionDate(DateTime.parse(dateString)).build();

    }

    private Transaction getTransactionWithExecutionDate(DateTime dateTime) {

        return Transaction.builder().onExecutionDate(dateTime).build();

    }

}