package pl.com.michalpolak.hyperbudget.account.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

import static org.junit.Assert.*;

public class NameIsRequiredTest {

    @Test
    public void validateShortName() {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(getAccountWithName("a"));
        } catch (InvalidAccountException e) {
            fail("Method not should throw InvalidAccountException.");
        }

       //then
        return;
    }

    @Test
    public void validateEmptyName() {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(getAccountWithName(""));
        } catch (InvalidAccountException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }

    @Test
    public void validateNullName() {
        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(getAccountWithName(null));
        } catch (InvalidAccountException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }

    @Test
    public void validateNullAccount() throws InvalidAccountException {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(null);
        } catch (NullPointerException e) {
            return;
        }

        //then
        fail("Method should throw InvalidAccountException.");
    }

    private Account getAccountWithName(String name) {
        return new Account(name);
    }

}