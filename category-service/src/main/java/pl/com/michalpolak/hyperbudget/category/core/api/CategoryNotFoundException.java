package pl.com.michalpolak.hyperbudget.category.core.api;

import java.text.MessageFormat;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(CategoryId id) {
        super(MessageFormat.format("Category with id \"{0}\" not found", id));
    }

}
