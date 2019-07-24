package pl.com.michalpolak.hyperbudget.account.core.api;

import org.apache.commons.lang.StringUtils;

public class AccountName {

    private final String value;

    private AccountName(String value) {
        this.value = value;
    }

    public static AccountName fromString(String value){
       return new AccountName(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isBlank() {

        return StringUtils.isBlank(value);
    }
}
