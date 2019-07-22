package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;

class CategoryDataAdapter extends Category {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDataAdapter.class);

    private CategoryDataAdapter(CategoryData categoryData) {
        super(categoryData.getId(), categoryData.getName());
    }

    public static CategoryDataAdapter of(CategoryData categoryData) {
        return new CategoryDataAdapter(categoryData);
    }
}
