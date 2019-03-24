package pl.com.michalpolak.hyperbudget.transaction.client.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("account-service")
interface AccountServiceClient {

    @RequestMapping(path="/api/accounts/{id}",method = RequestMethod.GET, headers = {"X-API-Version=1"})
    AccountData getAccount(@PathVariable("id") String accountId);

}