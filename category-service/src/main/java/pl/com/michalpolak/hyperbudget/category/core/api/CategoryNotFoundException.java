package pl.com.michalpolak.hyperbudget.category.core.api;

import java.text.MessageFormat;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String id) {
        super(MessageFormat.format("Category with id \"{0}\" not found", id));
    }

}
