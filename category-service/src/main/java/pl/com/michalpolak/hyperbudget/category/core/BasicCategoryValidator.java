package pl.com.michalpolak.hyperbudget.category.core;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

import java.util.Collections;
import java.util.List;

class BasicCategoryValidator implements CategoryValidator {

    private final List<ValidationRule>  rules;

    BasicCategoryValidator(List<ValidationRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    @Override
    public void validate(Category category) throws InvalidCategoryException {
       for(ValidationRule rule : rules){
           rule.validate(category);
       }
    }
}
