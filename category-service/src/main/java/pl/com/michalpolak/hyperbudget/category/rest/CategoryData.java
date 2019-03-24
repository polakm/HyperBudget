package pl.com.michalpolak.hyperbudget.category.rest;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;

public class CategoryData {

    private String id;

    private String name;

    private String type;

    public CategoryData() {
    }

    public CategoryData(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
}

    public CategoryData(Category category) {
    this(category.getId(),category.getName(), category.getType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
