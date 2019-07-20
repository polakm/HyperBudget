package pl.com.michalpolak.hyperbudget.account.rest;

class AccountData {

    private final String id;

    private final String name;

    AccountData(String name) {
        this.id = null;
        this.name = name;
    }


    AccountData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String getName() {
        return name;
    }
    
    String getId() {
        return id;
    }


}
