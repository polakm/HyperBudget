package pl.com.michalpolak.hyperbudget.transaction.client.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;

class CategoryData {

    private final String id;
    private final String name;

    @JsonCreator
    private CategoryData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    CategoryData of(String id, String name) {
        return new CategoryData(id, name);
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
