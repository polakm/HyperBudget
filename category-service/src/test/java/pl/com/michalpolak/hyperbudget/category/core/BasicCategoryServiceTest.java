package pl.com.michalpolak.hyperbudget.category.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryNotFoundException;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryService;
import pl.com.michalpolak.hyperbudget.category.data.InMemoryCategoryRepository;

import java.util.Set;

import static org.junit.Assert.*;

public class BasicCategoryServiceTest {

    @Test
    public void addCategory() throws CategoryNotFoundException, InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());
        Category category = getExampleCategory();

        //when
        categoryService.addCategory(category);
        Category resultCategory = categoryService.getCategory(category.getId());

        //then
        assertNotNull(resultCategory);

    }

    @Test
    public void categoryNotFoundException() {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());

        //when
        try {
            categoryService.getCategory("non-exist-category-id");
        } catch (CategoryNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw CategoryNotFoundException.");
    }

    @Test
    public void invalidCategoryException() {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());
        Category category = getExampleCategory();
        category.setName(null);

        //when
        try {
            categoryService.addCategory(category);
        } catch (InvalidCategoryException e) {
            return;
        }

        //then
        fail("Method should throw InvalidCategoryException.");
    }


    @Test
    public void updateCategory() throws CategoryNotFoundException, InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());

        Category category = getExampleCategory();
        categoryService.addCategory(category);

        Category updatedCategory = getExampleCategory();
        updatedCategory.setId(category.getId());
        updatedCategory.setName("updated");

        //when
        categoryService.updateCategory(updatedCategory);
        Category resultCategory = categoryService.getCategory(category.getId());

        //then
        assertEquals("updated", resultCategory.getName());

    }

    @Test
    public void removeCategory() throws CategoryNotFoundException, InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());
        Category category = getExampleCategory();

        //when
        categoryService.addCategory(category);
        categoryService.removeCategory(category.getId());

        //when
        try {
            categoryService.getCategory(category.getId());
        } catch (CategoryNotFoundException e) {
            return;
        }

        //then
        fail("After remove category, method getCategory should throw CategoryNotFoundException.");

    }


    @Test
    public void allCategories() throws InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(new InMemoryCategoryRepository());
        Category category1 = getExampleCategory();
        Category category2 = getExampleCategory();
        Category category3 = getExampleCategory();

        //when
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        categoryService.addCategory(category3);
        Set<Category> categories = categoryService.allCategories();

        //then
        assertEquals(3, categories.size());
    }



    private Category getExampleCategory() {
        Category category = new Category();
        category.setName("example-category-name");
        return category;
    }


}