package pl.com.michalpolak.hyperbudget.category.core;

import org.junit.Test;
import pl.com.michalpolak.hyperbudget.category.core.api.*;

import java.util.Set;

import static org.junit.Assert.*;

public class BasicCategoryServiceTest {

    @Test
    public void addCategory() throws CategoryNotFoundException, InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());
        Category category = createCategory("test-name");

        //when
        categoryService.addCategory(category);
        Category resultCategory = categoryService.getCategory(category.getId());

        //then
        assertNotNull(resultCategory);

    }

    @Test
    public void categoryNotFoundException() {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());

        //when
        try {
            categoryService.getCategory(CategoryId.generate());
        } catch (CategoryNotFoundException e) {
            return;
        }

        //then
        fail("Method should throw CategoryNotFoundException.");
    }

    @Test
    public void invalidCategoryException() {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());
        Category category = createCategory(null);

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
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());

        Category category = createCategory("test-name");
        categoryService.addCategory(category);

        Category updatedCategory = Category.of(category.getId(),CategoryName.fromString("updated"),CategoryType.EXPENSE);

        //when
        categoryService.updateCategory(updatedCategory);
        Category resultCategory = categoryService.getCategory(category.getId());

        //then
        assertEquals("updated", resultCategory.getName().toString());

    }

    @Test
    public void removeCategory() throws CategoryNotFoundException, InvalidCategoryException {

        //given
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());
        Category category = createCategory("test-name");

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
        CategoryService categoryService = CategoryServiceConfiguration.createCategoryService(InMemoryCategoryRepository.empty());
        Category category1 = createCategory("test-name-1");
        Category category2 = createCategory("test-name-2");
        Category category3 = createCategory("test-name-3");

        //when
        categoryService.addCategory(category1);
        categoryService.addCategory(category2);
        categoryService.addCategory(category3);
        Set<Category> categories = categoryService.allCategories();

        //then
        assertEquals(3, categories.size());
    }



    private Category createCategory(String name) {

        Category category = Category.of(CategoryName.fromString(name), CategoryType.INCOME);
        return category;
    }


}