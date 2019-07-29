package pl.com.michalpolak.hyperbudget.category.core.api;


import java.util.Set;

public interface CategoryService {

    Category addCategory(Category category) throws InvalidCategoryException;

    void removeCategory(CategoryId id) throws CategoryNotFoundException;

    Category getCategory(CategoryId id) throws CategoryNotFoundException;

    Set<Category> allCategories();

    Category updateCategory(Category category) throws CategoryNotFoundException;

    Set<Category> getCategoriesByType(CategoryType type);
}
