package pl.com.michalpolak.hyperbudget.category.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.michalpolak.hyperbudget.category.core.api.Category;
import pl.com.michalpolak.hyperbudget.category.core.spi.CategoryRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Configuration
public class DataLayerConfiguration {

   @Bean
   public static  CategoryRepository  categoryRepositoryBean(){

        String id;
        Map<String,Category> initialData = new HashMap<>();
        initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Other"));
        initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Shopping"));
        initialData.put(id = UUID.randomUUID().toString(),new Category(id, "Car"));
        initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Home"));
        initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Food"));
        initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Education"));

       return new InMemoryCategoryRepository(initialData);
    }

}
