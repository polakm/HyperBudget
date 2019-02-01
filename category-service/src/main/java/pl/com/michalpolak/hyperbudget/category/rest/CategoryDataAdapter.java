package pl.com.michalpolak.hyperbudget.category.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

class CategoryDataAdapter extends Category {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDataAdapter.class);

    CategoryDataAdapter(CategoryData categoryData) {
        super();
        setName(categoryData.getName());
    }

    public CategoryDataAdapter(String id, CategoryData categoryData) {
        this(categoryData);
        setId(id);
    }


}
