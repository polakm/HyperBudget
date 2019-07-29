package pl.com.michalpolak.hyperbudget.category.rest;

import org.springframework.stereotype.Component;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class CategoryDataMapper {

    CategoryData mapToData(Category category) {
        return mapEntityToData(category);
    }

    final CategoryData mapEntityToData(Category category) {
        return CategoryData.of(category.getId().toString(), category.getName().toString(), category.getType().toString());
    }

    Category mapToEntity(CategoryData category) {
        if (category.getId() == null) {
            Category.of(category.getName(), category.getType());
        }
        return Category.of(category.getId(), category.getName(), category.getType());
    }

    Category mapToEntity(String id, CategoryData category) {
        return Category.of(id, category.getName(), category.getType());
    }

    List<CategoryData> mapToDataList(Set<Category> categories) {
        return categories.stream().map(a -> mapEntityToData(a)).collect(Collectors.toList());
    }

}
