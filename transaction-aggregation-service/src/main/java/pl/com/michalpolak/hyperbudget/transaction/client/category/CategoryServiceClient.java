package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;

import java.util.HashMap;

@Component
class CategoryServiceClient implements CategoryService {

    private HashMap<String, Category> categories;

    CategoryServiceClient() {
        this.categories = new HashMap<>();
        categories.put("aaaaaa", new Category("aaaaaa", "Other"));
        categories.put("bbbbbb", new Category("bbbbbb", "Shopping"));
        categories.put("cccccc", new Category("cccccc", "Car"));
        categories.put("dddddd", new Category("dddddd", "Home"));
        categories.put("eeeeee", new Category("eeeeee", "Food"));
        categories.put("ffffff", new Category("ffffff", "Education"));
    }

    public Category getCategory(String categoryId) {
        return categories.get(categoryId);
    }

}