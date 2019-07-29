package pl.com.michalpolak.hyperbudget.category.data;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

interface InitialDataLoader {

   List<Category> loadAsList() throws IOException;

   Map<UUID, Category> loadAsMap() throws IOException;

}
