package pl.com.michalpolak.hyperbudget.category.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.category.core.api.CategoryService;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("pl.com.michalpolak")
class CategoryServiceConfiguration {

    @Bean
    public static CategoryValidator categoryValdiatorBean() {
        List<ValidationRule> rules = new ArrayList<>();
        rules.add(new NameIsRequired());
        return new BasicCategoryValidator(rules);
    }

    @Bean
    @Autowired
    public static CategoryService categoryServiceBean(CategoryRepository categoryRepository, CategoryValidator categoryValidator) {
        return new BasicCategoryService(categoryRepository, categoryValidator);
    }

    public static CategoryService createCategoryService(CategoryRepository categoryRepository) {
        CategoryValidator categoryValidator =  categoryValdiatorBean();
        return new BasicCategoryService(categoryRepository, categoryValidator);
    }

}
