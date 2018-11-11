package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.transaction.core.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {

    private TransactionService service;

    public TransactionRestController(@Autowired TransactionService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    TransactionData addTranasaction(@Valid @RequestBody TransactionData transactionData) throws InvalidTransactionException {

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
    TransactionData getTranaction(@PathVariable("id") String id) throws TransactionNotFoundException {
        return new TransactionData(service.getTransaction(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id){
        service.removeTransaction(id);
    }

    @RequestMapping(path ="/{id}", method = RequestMethod.PUT)
    void updateTranaction(@PathVariable("id") String id, @RequestBody TransactionData transactionData){

       Transaction transaction = new TransactionDataAdapter(id,transactionData);
        service.updateTransaction(transaction);
    }

    @ExceptionHandler({ TransactionNotFoundException.class })
    ResponseEntity handleTransactionNotExistException(TransactionNotFoundException exception, WebRequest request){
        return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ InvalidTransactionException.class })
    ResponseEntity handleInvalidTransactionException(InvalidTransactionException exception, WebRequest request){
        return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
