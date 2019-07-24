package pl.com.michalpolak.hyperbudget.account.core.api;

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

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
