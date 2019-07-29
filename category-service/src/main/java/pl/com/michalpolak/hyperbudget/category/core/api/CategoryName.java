package pl.com.michalpolak.hyperbudget.category.core.api;

import org.apache.commons.lang.StringUtils;

public class CategoryName {

    private final String value;

    private CategoryName(String value) {
        this.value = value;
    }

    public static CategoryName fromString(String value) {
        return new CategoryName(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isBlank() {
       return StringUtils.isBlank(value);
    }
}
