package pl.com.michalpolak.hyperbudget.transaction.core.api;

import java.util.UUID;

public class TransactionId {

    private final UUID value;

    private TransactionId(UUID value) {
        this.value = value;
    }

    public static TransactionId generate() {
        return new TransactionId(UUID.randomUUID());
    }

    public static TransactionId fromString(String value) {
        return new TransactionId(UUID.fromString(value));
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
        if (obj instanceof TransactionId) {
            return value.equals(((TransactionId) obj).value);
        }
        return false;
    }
}
