package pl.com.michalpolak.hyperbudget.category.core.api;

import java.util.UUID;

public class CategoryId {

    private final UUID value;

    private CategoryId(UUID value) {
        this.value = value;
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }

    public static CategoryId fromString(String value) {
        return new CategoryId(UUID.fromString(value));
    }

    public UUID toUUID() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
