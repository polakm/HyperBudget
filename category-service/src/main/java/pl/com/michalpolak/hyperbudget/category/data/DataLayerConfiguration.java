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

    public static final String EXPENSE = "expense";
    public static final String INCOME = "income";

    @Bean
   public static  CategoryRepository  categoryRepositoryBean(){

        String id;
        Map<String,Category> initialData = new HashMap<>();
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Other", EXPENSE));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id, "Car", EXPENSE));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Home", EXPENSE));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Food", EXPENSE));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Education", EXPENSE));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Shopping", EXPENSE));

       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Other", INCOME));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Donation", INCOME));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Trade", INCOME));
       initialData.put(id = UUID.randomUUID().toString(),new Category(id,"Salary", INCOME));

       return new InMemoryCategoryRepository(initialData);
    }

}
