package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.core.style.ToStringCreator;

public class Account {

    private final String id;

    private final String name;

    protected Account(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Account of(String id, String name) {
        return new Account(id, name);
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
                .append("id", id)
                .append("name", name).toString();
    }

}
