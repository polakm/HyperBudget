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
    Transaction addTranasaction(@RequestBody TransactionData transactionData){

        Transaction transaction = new Transaction(transactionData);

        return service.addTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.GET)
    Set<Transaction> transactionList(){
        return service.allTrascations();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    Transaction getTranaction(@PathVariable("id") String id){
       return service.getTransaction(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id){
        service.removeTransaction(id);
    }

}
