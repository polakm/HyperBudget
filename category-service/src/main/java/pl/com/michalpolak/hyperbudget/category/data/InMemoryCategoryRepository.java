package pl.com.michalpolak.hyperbudget.category.data;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<UUID, Category> storage;

    private InMemoryCategoryRepository() {
        this.storage = new ConcurrentHashMap<>();
    }


    private InMemoryCategoryRepository(Map<UUID, Category> initialData) {
        this.storage = new ConcurrentHashMap<>(initialData);
    }

    static InMemoryCategoryRepository of(Map<UUID, Category> data) {
        return new InMemoryCategoryRepository(data);
    }

    static InMemoryCategoryRepository empty() {
        return new InMemoryCategoryRepository();
    }

    @Override
    public Category save(Category category) {

        this.storage.put(category.getId().toUUID(), category);
        return category;
    }

    @Override
    public Set<Category> getAll() {

        return new HashSet(this.storage.values());
    }

    @Override
    public void remove(UUID id) {

        this.storage.remove(id);
    }

    @Override
    public Category findById(UUID id) {

        return this.storage.get(id);
    }

    @Override
    public Category update(Category category) {

        this.storage.put(category.getId().toUUID(), category);
        return category;
    }
}
