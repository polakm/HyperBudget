package pl.com.michalpolak.hyperbudget.category.core.api;

import java.util.UUID;

public class Category {

    private String id;
    private String name;

    public Category(){
        setId(UUID.randomUUID().toString());
    }

    public Category(String id, String name){
        this.id =id;
        this.name=name;
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
}
