package pl.com.michalpolak.hyperbudget.category.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryNotFoundException;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryService;

import java.util.Set;
import java.util.stream.Collectors;


class BasicCategoryService implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicCategoryService.class);

    private CategoryRepository categoryRepository;
    private CategoryValidator validator;

    BasicCategoryService(CategoryRepository categoryRepository, CategoryValidator validator) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public Category addCategory(Category category) throws t {

        LOGGER.info("Add new category - Category ID: {}", category.getId());
        validator.validate(category);
        Category result = categoryRepository.save(category);
        LOGGER.info("New category has added - Category ID: {}", category.getId());
        return result;
    }

    @Override
    public void removeCategory(String id) throws CategoryNotFoundException {

        LOGGER.info("Remove category - Category ID: {}", id);
        this.getCategory(id);
        this.categoryRepository.remove(id);
        LOGGER.info("Category has removed - Category ID: {}", id);
    }

    @Override
    public Category getCategory(String id) throws CategoryNotFoundException {

        Category result = this.categoryRepository.findById(id);
        if (result == null) {
            throw new CategoryNotFoundException(id);
        }
        return result;
    }

    @Override
    public Set<Category> allCategories() {
        return this.categoryRepository.getAll();
    }

    @Override
    public Category updateCategory(Category category) throws CategoryNotFoundException {

        LOGGER.info("Update category - Category ID: {}", category.getId());
        this.getCategory(category.getId());
        Category result = this.categoryRepository.update(category);
        LOGGER.info("Category has updated - Category ID: {}", category.getId());
        return result;
    }

    @Override
    public Set<Category> getCategoriesByType(String type) {

        return this.categoryRepository.getAll().stream().filter(c-> c.getType().equals(type)).collect(Collectors.toSet());
    }
}

