package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.transaction.core.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/transactions")
public class TransactionRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    private TransactionService service;

    public TransactionRestController(@Autowired TransactionService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    TransactionData addTranasaction(@RequestBody TransactionData transactionData) throws InvalidTransactionException {

        Transaction transaction = new TransactionDataAdapter(transactionData);
        service.addTransaction(transaction);
        return new TransactionData(transaction);
    }

    @RequestMapping(method = RequestMethod.GET)
    TransactionDataList transactionList() {

        Set<Transaction> transactions = service.allTrascations();
        return new TransactionDataList(transactions);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    TransactionData getTranaction(@PathVariable("id") String id) throws TransactionNotFoundException {

        return new TransactionData(service.getTransaction(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTranaction(@PathVariable("id") String id) throws TransactionNotFoundException {

        service.removeTransaction(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateTranaction(@PathVariable("id") String id, @RequestBody TransactionData transactionData) throws TransactionNotFoundException {

        Transaction transaction = new TransactionDataAdapter(id, transactionData);
        service.updateTransaction(transaction);
    }

    @ExceptionHandler({TransactionNotFoundException.class})
    ResponseEntity handleTransactionNotExistException(TransactionNotFoundException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(),exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidTransactionException.class})
    ResponseEntity handleInvalidTransactionException(InvalidTransactionException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(),exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {

        LOGGER.error(exception.getMessage(),exception);
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
