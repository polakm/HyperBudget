package pl.com.michalpolak.hyperbudget.transaction.client.account;

import com.google.gson.Gson;

public class AccountData {

    private String id;

    private String name;

    public AccountData(){

    }

    public AccountData(String id, String name) {
        this.id= id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
