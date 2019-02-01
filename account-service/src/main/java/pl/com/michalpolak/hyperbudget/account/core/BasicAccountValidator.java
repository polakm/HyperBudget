package pl.com.michalpolak.hyperbudget.account.core;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

import java.util.List;

public class BasicAccountValidator implements AccountValidator {

    private List<ValidationRule>  rules;

    public BasicAccountValidator(List<ValidationRule> rules) {
        this.rules = rules;
    }

    @Override
    public void validate(Account account) throws InvalidAccountException {
       for(ValidationRule rule : rules){
           rule.validate(account);
       }
    }
}
