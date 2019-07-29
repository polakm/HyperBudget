package pl.com.michalpolak.hyperbudget.category.core.spi;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository {

    Category save(Category category);

    Set<Category> getAll();

    void remove(UUID id);

    Category findById(UUID id);

    Category update(Category category);
}
