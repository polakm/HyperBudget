package pl.com.michalpolak.hyperbudget.transaction.client.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;

class AccountData {

    private final String id;
    private final String name;

    @JsonCreator
    private AccountData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    static AccountData of(String id, String name) {
        return new AccountData(id, name);
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
