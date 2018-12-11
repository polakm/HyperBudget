package pl.com.michalpolak.hyperbudget.category.data;

import org.springframework.stereotype.Repository;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {

    private Map<String, Category> storage;

    public InMemoryCategoryRepository() {
        this.storage = new ConcurrentHashMap<>();
        initDefaults();
    }


    private void initDefaults() {
        String id;
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id,"Other"));
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id,"Shopping"));
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id, "Car"));
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id,"Home"));
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id,"Food"));
        this.storage.put(id = UUID.randomUUID().toString(),new Category(id,"Education"));
    }

    @Override
    public Category save(Category category) {

        this.storage.put(category.getId(), category);
        return category;
    }

    @Override
    public Set<Category> getAll() {

        return new HashSet(this.storage.values());
    }

    @Override
    public void remove(String id) {

        this.storage.remove(id);
    }

    @Override
    public Category findById(String id) {

        return this.storage.get(id);
    }

    @Override
    public Category update(Category category) {

        this.storage.put(category.getId(), category);
        return category;
    }
}
