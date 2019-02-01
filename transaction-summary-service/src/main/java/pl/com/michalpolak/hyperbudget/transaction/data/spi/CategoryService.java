package pl.com.michalpolak.hyperbudget.transaction.data.spi;

import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;

public interface CategoryService {
    Category getCategory(String categoryId);
}
