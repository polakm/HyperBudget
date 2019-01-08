package pl.com.michalpolak.hyperbudget.category.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryNotFoundException;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryService;
import pl.com.michalpolak.hyperbudget.category.core.api.InvalidCategoryException;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);

    private CategoryService service;

    public CategoryRestController(@Autowired CategoryService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    CategoryData addCategory(@RequestBody CategoryData categoryData) throws InvalidCategoryException {

        Category category = new CategoryDataAdapter(categoryData);
        service.addCategory(category);
        return new CategoryData(category.getId(), category.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    List<CategoryData> categoryList() {

        Set<Category> categories = service.allCategories();
        return new CategoryDataList(categories).asList();
    }

    @RequestMapping(method = RequestMethod.GET, params = "type")
    List<CategoryData> categoriesByType(@QueryParam("type") String type) {

        Set<Category> categories = service.getCategoriesByType(type);
        return new CategoryDataList(categories).asList();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    CategoryData getCategory(@PathVariable("id") String id) throws CategoryNotFoundException {
        return new CategoryData(service.getCategory(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeCategory(@PathVariable("id") String id) throws CategoryNotFoundException {

        service.removeCategory(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateCategory(@PathVariable("id") String id, @RequestBody CategoryData categoryData) throws CategoryNotFoundException {

        Category category = new CategoryDataAdapter(id, categoryData);
        service.updateCategory(category);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    ResponseEntity handleCategoryNotExistException(CategoryNotFoundException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCategoryException.class})
    ResponseEntity handleInvalidCategoryException(InvalidCategoryException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {

        LOGGER.error(exception.getMessage(), exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
