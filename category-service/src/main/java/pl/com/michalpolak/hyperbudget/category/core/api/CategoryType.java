package pl.com.michalpolak.hyperbudget.category.core.api;

public enum CategoryType {

    INCOME,
    EXPENSE;

    public static CategoryType fromString(String value) {
        if (value == null) {
            return null;
        }
        return CategoryType.valueOf(value.toUpperCase());
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
