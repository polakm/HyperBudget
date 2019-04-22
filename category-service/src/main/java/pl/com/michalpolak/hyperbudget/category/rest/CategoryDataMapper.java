package pl.com.michalpolak.hyperbudget.category.rest;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class CategoryDataMapper {

    CategoryData mapToData(Category category) {
        return new CategoryData(category.getId(), category.getName(),category.getType());
    }

    Category mapToEntity(CategoryData category) {
        if (category.getId() == null) {
            new Category(category.getName(),category.getType());
        }
        return new Category(category.getId(), category.getName(),category.getType());
    }

    Category mapToEntity(String id, CategoryData category) {
        return new Category(id, category.getName(),category.getType());
    }

    List<CategoryData> mapToDataList(Set<Category> categories) {
        return categories.stream().map(a -> mapToData(a)).collect(Collectors.toList());
    }

}
