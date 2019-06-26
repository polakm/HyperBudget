package pl.com.michalpolak.hyperbudget.category.data;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<String, Category> storage;

    public InMemoryCategoryRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    public InMemoryCategoryRepository(Map<String,Category> initialData) {
        this.storage = new ConcurrentHashMap<>(initialData);
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
