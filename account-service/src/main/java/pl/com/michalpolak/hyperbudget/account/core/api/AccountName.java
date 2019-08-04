package pl.com.michalpolak.hyperbudget.account.core.api;

import org.apache.commons.lang.StringUtils;

public class AccountName {

    private final String value;

    private AccountName(String value) {
        this.value = value;
    }

    public static AccountName fromString(String value) {
        return new AccountName(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isBlank() {

        return StringUtils.isBlank(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof AccountName) {
            return value.equals(((AccountName) obj).value);
        }
        return false;
    }
}
