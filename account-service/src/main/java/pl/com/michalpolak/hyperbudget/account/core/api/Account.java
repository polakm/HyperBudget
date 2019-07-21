package pl.com.michalpolak.hyperbudget.account.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.UUID;

public class Account {

    private final String id;
    private final String name;

    public Account(String name){
        this.id= UUID.randomUUID().toString();
        this.name= name;
    }

    public Account(String id, String name){
        this.id =id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
      return new ToStringCreator(this)
              .append("id",id)
              .append("name",name).toString();
    }
}
