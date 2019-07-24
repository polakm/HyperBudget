package pl.com.michalpolak.hyperbudget.account.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.Objects;
import java.util.UUID;

public class Account {

    private final AccountId id;
    private final AccountName name;

    private Account(AccountName name){
        this.id= AccountId.generate();
        this.name= name;
    }

    private Account(AccountId id, AccountName name){
        this.id = id;
        this.name=name;
    }

    public static Account of(String id, String name) {
       return new Account(AccountId.fromString(id), AccountName.fromString(name));
    }

    public static Account of(AccountId id, AccountName name) {
        return new Account(id, name);
    }

    public static Account of( String name) {
        return new Account(AccountName.fromString(name));
    }

    public AccountId getId() {
        return id;
    }

    public AccountName getName() {
        return name;
    }

    @Override
    public String toString() {
      return new ToStringCreator(this)
              .append("id", id)
              .append("name", name).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
