package pl.com.michalpolak.hyperbudget.account.rest;

public class AccountData {

    private final String id;

    private final String name;

    public AccountData(String name) {
        this.id = null;
        this.name = name;
    }


    public AccountData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getId() {
        return id;
    }


}
