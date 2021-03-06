package pl.com.michalpolak.hyperbudget.category.core;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

interface CategoryValidator {

    void validate(Category category) throws InvalidCategoryException;
}
