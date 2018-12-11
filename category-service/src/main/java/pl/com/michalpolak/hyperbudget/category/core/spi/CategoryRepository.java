package pl.com.michalpolak.hyperbudget.category.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.util.Set;

@Repository
public interface CategoryRepository {

    Category save(Category category);

    Set<Category> getAll();

    void remove(String id);

    Category findById(String id);

    Category update(Category category);
}
