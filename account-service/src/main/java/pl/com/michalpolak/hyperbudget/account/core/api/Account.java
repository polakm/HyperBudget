package pl.com.michalpolak.hyperbudget.account.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.Objects;
import java.util.UUID;

public class Account {

    private final String id;
    private final String name;

    private Account(String name){
        this.id= UUID.randomUUID().toString();
        this.name= name;
    }

    private Account(String id, String name){
        this.id =id;
        this.name=name;
    }

    public static Account of(String id, String name) {
       return new Account(id, name);
    }

    public static Account of( String name) {
        return new Account(name);
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
