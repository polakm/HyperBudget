package pl.com.michalpolak.hyperbudget.category.core;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

import java.util.List;

public class BasicCategoryValidator implements CategoryValidator {

    private List<ValidationRule>  rules;

    public BasicCategoryValidator(List<ValidationRule> rules) {
        this.rules = rules;
    }

    @Override
    public void validate(Category category) throws InvalidCategoryException {
       for(ValidationRule rule : rules){
           rule.validate(category);
       }
    }
}
