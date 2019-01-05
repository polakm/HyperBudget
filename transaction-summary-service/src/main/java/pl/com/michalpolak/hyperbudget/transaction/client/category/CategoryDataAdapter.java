package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Category;

class CategoryDataAdapter extends Category {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDataAdapter.class);

    CategoryDataAdapter(CategoryData categoryData) {

        this.setId(categoryData.getId());
        this.setName(categoryData.getName());

    }

}
