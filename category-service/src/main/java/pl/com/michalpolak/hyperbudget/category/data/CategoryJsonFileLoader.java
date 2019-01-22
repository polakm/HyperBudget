package pl.com.michalpolak.hyperbudget.category.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CategoryJsonFileLoader implements InitialDataLoader {

    private InputStream dataStream;

    CategoryJsonFileLoader(InputStream dataStream) {
        this.dataStream = dataStream;
    }

    CategoryJsonFileLoader(String path) throws IOException {
        this.dataStream = new ClassPathResource(path).getInputStream();
    }

    public List<Category> loadAsList() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Category>) objectMapper.readValue(dataStream, new TypeReference<List<Category>>() {
        });

    }

    public Map<String, Category> loadAsMap() throws IOException {
        return loadAsList().stream().collect(Collectors.toMap(Category::getId, item -> item));
    }
}
