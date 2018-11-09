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
    Set<Transaction> transactionsList(){
        return service.allTrascations();
    }


}
