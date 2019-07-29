package pl.com.michalpolak.hyperbudget.category.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

import java.text.MessageFormat;

class NameIsRequired implements ValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(NameIsRequired.class);

    private static final String MESSAGE_PATTERN = "Name in category with id \"{0}\" is null or empty. The category name is required value.";

    @Override
    public void validate(Category category) throws InvalidCategoryException {

        if (category.getName() == null) {
            throw new InvalidCategoryException(MessageFormat.format(MESSAGE_PATTERN, category.getId()));
        }

        if (category.getName().isBlank()) {
            throw new InvalidCategoryException(MessageFormat.format(MESSAGE_PATTERN, category.getId()));
        }

    }
}
