package pl.com.michalpolak.hyperbudget.category.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.Objects;
import java.util.UUID;

public class Category {

    public interface Types {
        String INCOME = "income";
        String EXPENSE = "expense";
    }

    private final String id;
    private final String name;
    private final String type;

    private Category(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    private Category(String name, String type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
    }

    public static Category of(String name, String type) {
        return new Category(name, type);
    }

    public static Category of(String id, String name, String type) {
        return new Category(id, name, type);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", id)
                .append("name", name)
                .append("type", type).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
