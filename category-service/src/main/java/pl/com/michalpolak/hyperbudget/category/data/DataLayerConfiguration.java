package pl.com.michalpolak.hyperbudget.category.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
class DataLayerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLayerConfiguration.class);

    @Bean
    public static CategoryRepository categoryRepositoryBean() {

        Map<UUID, Category> initialData = loadInitialData();
        return InMemoryCategoryRepository.of(initialData);
    }

    private static Map<UUID, Category> loadInitialData() {

        try {
            InitialDataLoader initialDataLoader = initialDataLoader();
            return initialDataLoader.loadAsMap();
        } catch (IOException e) {
            LOGGER.error("Error during load initial data.", e);
            return new HashMap<>();
        }
    }

    private static InitialDataLoader initialDataLoader() throws IOException {

        return new CategoryJsonFileLoader("classpath:initial.data.json");
    }

}
