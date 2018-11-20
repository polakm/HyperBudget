package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.CategoryService;

@Component
class CategoryServiceClient implements CategoryService {

    public Category getCategory(String categoryId) {
        return null;
    }
}
