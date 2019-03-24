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
@RequestMapping(path="/api/categories",  headers = {"X-API-Version=1"})
public class CategoryRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);

    private CategoryService service;
    private CategoryDataMapper mapper;
    @Autowired
    public CategoryRestController( CategoryService service, CategoryDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    CategoryData addCategory(@RequestBody CategoryData categoryData) throws InvalidCategoryException {

        Category category = mapper.mapToEntity(categoryData);
        service.addCategory(category);
        return mapper.mapToData(category);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<CategoryData> categoryList() {

        Set<Category> categories = service.allCategories();
        return mapper.mapToDataList(categories);
    }

    @RequestMapping(method = RequestMethod.GET, params = "type")
    List<CategoryData> categoriesByType(@QueryParam("type") String type) {

        Set<Category> categories = service.getCategoriesByType(type);
        return mapper.mapToDataList(categories);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    CategoryData getCategory(@PathVariable("id") String id) throws CategoryNotFoundException {

        Category category = service.getCategory(id);
        return mapper.mapToData(category);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeCategory(@PathVariable("id") String id) throws CategoryNotFoundException {

        service.removeCategory(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateCategory(@PathVariable("id") String id, @RequestBody CategoryData categoryData) throws CategoryNotFoundException {

        Category category = mapper.mapToEntity(id, categoryData);
        service.updateCategory(category);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    ResponseEntity handleCategoryNotExistException(CategoryNotFoundException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("b4e618","Category not found", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidCategoryException.class})
    ResponseEntity handleInvalidCategoryException(InvalidCategoryException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("c38984","Invalid category", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {

        LOGGER.error(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("43514a","Unknown Error", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
