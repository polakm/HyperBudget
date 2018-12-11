package pl.com.michalpolak.hyperbudget.transaction.client.category;

public class CategoryData {

    private String id;

    private String name;

    public CategoryData(){

    }

    public CategoryData(String id, String name) {
        this.id= id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
