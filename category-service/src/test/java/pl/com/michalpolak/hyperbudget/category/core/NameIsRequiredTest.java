package pl.com.michalpolak.hyperbudget.category.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

import static org.junit.Assert.*;

public class NameIsRequiredTest {

    @Test
    public void validateShortName() throws InvalidCategoryException {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        rule.validate(getCategoryWithName("a"));

        //then
        assertTrue(true);
    }

    @Test
    public void validateEmptyName() {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(getCategoryWithName(""));
        } catch (InvalidCategoryException e) {
            return;
        }

        //then
        fail("Method should throw InvalidCategoryException.");
    }

    @Test
    public void validateNullName() {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(getCategoryWithName(null));
        } catch (InvalidCategoryException e) {
            return;
        }

        //then
        fail("Method should throw InvalidCategoryException.");
    }

    @Test
    public void validateNullCategory() throws InvalidCategoryException {

        //given
        ValidationRule rule = new NameIsRequired();

        //when
        try {
            rule.validate(null);
        } catch (NullPointerException e) {
            return;
        }

        //then
        fail("Method should throw InvalidCategoryException.");
    }

    private Category getCategoryWithName(String name) {
        return Category.of(name, null);
    }

}