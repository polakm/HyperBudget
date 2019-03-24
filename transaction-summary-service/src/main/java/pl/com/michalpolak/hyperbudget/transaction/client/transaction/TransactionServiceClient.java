package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("transaction-service")
public interface TransactionServiceClient {

    @RequestMapping(path="/api/transactions",method = RequestMethod.GET, headers = {"X-API-Version=1"})
    List<TransactionData> transactionList();

}
