package pl.com.michalpolak.hyperbudget.category.rest;

public class CategoryData {

    private final String id;

    private final String name;

    private final String type;

    public CategoryData(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public CategoryData(String name, String type) {
        this.id = null;
        this.name = name;
        this.type = type;
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


}
