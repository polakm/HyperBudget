package pl.com.michalpolak.hyperbudget.transaction.client.category;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("category-service")
interface CategoryServiceClient {

    @RequestMapping(path="/api/categories/{id}",method = RequestMethod.GET)
    public CategoryData getCategory(@PathVariable("id") String categoryId);

}