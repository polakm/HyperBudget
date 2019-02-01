package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("transaction-service")
public interface TransactionServiceClient {


    @RequestMapping(path="/api/transactions",method = RequestMethod.POST, headers = {"X-API-Version=1"})
    TransactionData addTranasaction(@RequestBody TransactionData transactionData);


    @RequestMapping(path="/api/transactions",method = RequestMethod.GET, headers = {"X-API-Version=1"})
    List<TransactionData> transactionList();


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.GET, headers = {"X-API-Version=1"})
    TransactionData getTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.DELETE, headers = {"X-API-Version=1"})
    void removeTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.PUT, headers = {"X-API-Version=1"})
    void updateTranaction(@PathVariable("id") String id, @RequestBody TransactionData transactionData);

}
