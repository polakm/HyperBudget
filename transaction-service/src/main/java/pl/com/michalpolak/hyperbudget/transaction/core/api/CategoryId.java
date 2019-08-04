package pl.com.michalpolak.hyperbudget.transaction.core.api;

import java.util.UUID;

public class CategoryId {

    private final UUID value;

    CategoryId(UUID value) {
        this.value = value;
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }

    public static CategoryId fromString(String id) {
        return new CategoryId(UUID.fromString(id));
    }

    public UUID toUUID() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CategoryId) {
            return value.equals(((CategoryId) obj).value);
        }
        return false;
    }

}
