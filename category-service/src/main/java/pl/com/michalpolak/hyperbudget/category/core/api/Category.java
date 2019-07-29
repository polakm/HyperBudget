package pl.com.michalpolak.hyperbudget.category.core.api;

import org.springframework.core.style.ToStringCreator;

import java.util.Objects;

public class Category {

    private final CategoryId id;
    private final CategoryName name;
    private final CategoryType type;

    private Category(CategoryId id, CategoryName name, CategoryType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    private Category(CategoryName name, CategoryType type) {
        this.id = CategoryId.generate();
        this.name = name;
        this.type = type;
    }

    public static Category of(CategoryName name, CategoryType type) {
        return new Category(name, type);
    }

    public static Category of(CategoryId id, CategoryName name, CategoryType type) {
        return new Category(id, name, type);
    }

    public static Category of(String name, String type) {
        return new Category(CategoryName.fromString(name), CategoryType.fromString(type));
    }

    public static Category of(String id, String name, String type) {
        return new Category(CategoryId.fromString(id), CategoryName.fromString(name), CategoryType.fromString(type));
    }

    public CategoryName getName() {
        return name;
    }

    public CategoryId getId() {
        return id;
    }

    public CategoryType getType() {
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
