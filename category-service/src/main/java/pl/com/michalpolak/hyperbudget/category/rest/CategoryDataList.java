package pl.com.michalpolak.hyperbudget.category.rest;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryDataList {

    private List<CategoryData> categories;

    public CategoryDataList(Set<Category> categorySet) {

        categories = new ArrayList<>();

        categorySet.forEach(c -> {
            categories.add(new CategoryData(c));
        });

    }


    public List<CategoryData> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryData> categories) {
        this.categories = categories;
    }

    public List<CategoryData> asList() {
        return categories;
    }
}
