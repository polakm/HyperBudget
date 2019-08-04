package pl.com.michalpolak.hyperbudget.transaction.core.spi;

import org.springframework.core.style.ToStringCreator;

public class Category {

    private final String id;

    private final String name;

    protected Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Category of(String id, String name) {
        return new Category(id, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj instanceof Category) {
            return id.equals(((Category) obj).id)
                    && (((Category) obj).name).equals(name) || ((((Category) obj).name) == null);
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("name", name).toString();
    }

}
