package pl.com.michalpolak.hyperbudget.category.data;

import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.io.IOException;
import java.util.List;
import java.util.Map;

interface InitialDataLoader {

   List<Category> loadAsList() throws IOException;

   Map<String, Category> loadAsMap() throws IOException;

}
