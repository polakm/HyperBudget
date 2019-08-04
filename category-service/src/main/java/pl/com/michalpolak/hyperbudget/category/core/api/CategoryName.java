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

    public boolean isBlank() {
        return StringUtils.isBlank(value);
    }

    @Override
    public String toString() {
        return value;
    }


    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof CategoryName) {
            return value.equals(((CategoryName) obj).value);
        }
        return false;
    }
}
