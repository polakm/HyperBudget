package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

import java.util.Set;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

    private TransactionService service;

    public TransactionRestController(@Autowired TransactionService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    TransactionData addTranasaction(@RequestBody TransactionData transactionData){

        Transaction transaction = new TransactionDataAdapter(transactionData);
        service.addTransaction(transaction);
        return new TransactionData(transaction);
    }

    @RequestMapping(method = RequestMethod.GET)
    TransactionDataList transactionList(){
         Set<Transaction> transactions = service.allTrascations();
        return new TransactionDataList(transactions);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    TransactionData getTranaction(@PathVariable("id") String id){
       return new TransactionData(service.getTransaction(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id){
        service.removeTransaction(id);
    }

}
