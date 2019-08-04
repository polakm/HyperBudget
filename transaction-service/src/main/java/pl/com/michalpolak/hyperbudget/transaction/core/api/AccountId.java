package pl.com.michalpolak.hyperbudget.transaction.core.api;

import java.util.UUID;

public class AccountId {

    private final UUID value;

    AccountId(UUID value) {
        this.value = value;
    }

    public static AccountId generate() {
        return new AccountId(UUID.randomUUID());
    }

    public static AccountId fromString(String id) {
        return new AccountId(UUID.fromString(id));
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
        if (obj instanceof AccountId) {
            return value.equals(((AccountId) obj).value);
        }
        return false;
    }

}
