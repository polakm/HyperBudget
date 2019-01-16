package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.data.spi.CategoryService;

@Component
public class CategoryAdapter implements CategoryService {

    private CategoryServiceClient client;

    @Autowired
    public CategoryAdapter(CategoryServiceClient client) {
        this.client = client;
    }

    @Override
    public Category getCategory(String id) {
        return new CategoryDataAdapter(client.getCategory(id));
    }
}
