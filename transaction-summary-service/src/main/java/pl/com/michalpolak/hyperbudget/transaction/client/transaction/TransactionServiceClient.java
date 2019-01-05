package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

import java.util.List;

@FeignClient("transaction-service")
public interface TransactionServiceClient {


    @RequestMapping(path="/api/transactions",method = RequestMethod.POST)
    TransactionData addTranasaction(@RequestBody TransactionData transactionData);


    @RequestMapping(path="/api/transactions",method = RequestMethod.GET)
    List<TransactionData> transactionList();


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.GET)
    TransactionData getTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/api/transactions/{id}", method = RequestMethod.PUT)
    void updateTranaction(@PathVariable("id") String id, @RequestBody TransactionData transactionData);

}
