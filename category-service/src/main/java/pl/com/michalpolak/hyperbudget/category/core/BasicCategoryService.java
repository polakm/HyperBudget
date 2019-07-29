package pl.com.michalpolak.hyperbudget.category.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.category.core.api.*;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;


class BasicCategoryService implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicCategoryService.class);

    private final CategoryRepository categoryRepository;
    private final CategoryValidator validator;

    BasicCategoryService(CategoryRepository categoryRepository, CategoryValidator validator) {
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public Category addCategory(Category category) throws InvalidCategoryException  {

        LOGGER.info("Add new category - Category ID: {}", category.getId());
        validator.validate(category);
        Category result = categoryRepository.save(category);
        LOGGER.info("New category has added - Category ID: {}", category.getId());
        return result;
    }

    @Override
    public void removeCategory(CategoryId id) throws CategoryNotFoundException {

        LOGGER.info("Remove category - Category ID: {}", id);
        this.getCategory(id);
        this.categoryRepository.remove(id.toUUID());
        LOGGER.info("Category has removed - Category ID: {}", id);
    }

    @Override
    public Category getCategory(CategoryId id) throws CategoryNotFoundException {

        Category result = this.categoryRepository.findById(id.toUUID());
        if (result == null) {
            throw new CategoryNotFoundException(id);
        }
        return result;
    }

    @Override
    public Set<Category> allCategories() {
        return Collections.unmodifiableSet(this.categoryRepository.getAll());
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
    public Set<Category> getCategoriesByType(CategoryType type) {

        Set<Category> result = this.categoryRepository.getAll().stream().filter(c-> c.getType().equals(type)).collect(Collectors.toSet());
    return Collections.unmodifiableSet(result);
    }
}

