package pl.com.michalpolak.hyperbudget.account.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.spi.AccountRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
class DataLayerConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLayerConfiguration.class);

    @Bean
    static AccountRepository categoryRepositoryBean() {
        Map<String, Account> initialData = loadInitialData();
        return InMemoryAccountRepository.of(initialData);
    }

    private static Map<String, Account> loadInitialData() {
        try {
            InitialDataLoader initialDataLoader = initialDataLoader();
            return   initialDataLoader.loadAsMap();
        } catch (IOException e) {
            LOGGER.error("Error during load initial data.", e);
            return Collections.emptyMap();
        }
    }

    private static InitialDataLoader initialDataLoader() throws IOException {
        return AccountJsonFileLoader.fromJsonFile("classpath:initial.data.json");
    }

}
