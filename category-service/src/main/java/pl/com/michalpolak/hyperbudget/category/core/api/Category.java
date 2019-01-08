package pl.com.michalpolak.hyperbudget.category.core.api;

import java.util.UUID;

public class Category {

    private String id;
    private String name;
    private String type;

    public Category(){
        setId(UUID.randomUUID().toString());
    }

    public Category(String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
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
