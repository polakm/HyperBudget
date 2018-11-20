package pl.com.michalpolak.hyperbudget.transaction.client.transaction;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.spi.TransactionService;

import java.util.List;


public interface TransactionServiceClient extends TransactionService {


    @RequestMapping(method = RequestMethod.POST)
    Transaction addTranasaction(@RequestBody Transaction transactionData);


    @RequestMapping(method = RequestMethod.GET)
    List<Transaction> transactionList();


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Transaction getTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id);


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateTranaction(@PathVariable("id") String id, @RequestBody Transaction transactionData);

}
