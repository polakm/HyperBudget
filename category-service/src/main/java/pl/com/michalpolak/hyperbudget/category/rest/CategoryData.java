package pl.com.michalpolak.hyperbudget.category.rest;

import com.google.gson.Gson;

class CategoryData {

    private final String id;

    private final String name;

    private final String type;

    CategoryData(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    CategoryData(String name, String type) {
        this.id = null;
        this.name = name;
        this.type = type;
    }

    String getName() {
        return name;
    }


    String getId() {
        return id;
    }


    String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
