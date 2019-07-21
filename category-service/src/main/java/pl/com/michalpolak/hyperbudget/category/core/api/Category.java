package pl.com.michalpolak.hyperbudget.category.core.api;

import java.util.UUID;

public class Category {

   public interface Types {
        String INCOME = "income";
        String EXPENSE ="expense";
    }

    private final String id;
    private final String name;
    private final String type;

    public Category(String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Category(String name, String type){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
    }

    static Category incomeCategory(String id ,String name){
        return new Category(name, Types.INCOME);
    }

    static Category incomeCategory(String name){
        return new Category(name, Types.INCOME);
    }

    static Category expenseCategory(String id , String name){
        return new Category(name, Types.EXPENSE);
    }

    static Category expenseCategory(String name){
        return new Category(name, Types.EXPENSE);
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
