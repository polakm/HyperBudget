package pl.com.michalpolak.hyperbudget.account.rest;

import com.google.gson.Gson;

class AccountData {

    private final String id;
    private final String name;

    private  AccountData(String name) {
        this.id = null;
        this.name = name;
    }

    private AccountData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AccountData of(String id, String name) {
        return new AccountData(id,name);
    }

    public static AccountData of(String name) {
        return new AccountData(name);
    }

    String getName() {
        return name;
    }
    
    String getId() {
        return id;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
