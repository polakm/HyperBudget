package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.transaction.core.api.InvalidTransactionException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionNotFoundException;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/api/transactions",  headers = {"X-API-Version=1"})
public class TransactionRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    private TransactionService service;
    private TransactionDataMapper mapper;

    @Autowired
    public TransactionRestController( TransactionService service, TransactionDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.POST)
    void addTranasaction(@RequestBody TransactionData transactionData) throws InvalidTransactionException {

        Transaction transaction = mapper.mapToEntity(transactionData);
        service.addTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<TransactionData> transactionList() {

        Set<Transaction> transactions = service.allTrascations();
        return mapper.mapToDataList(transactions);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    TransactionData getTransaction(@PathVariable("id") String id) throws TransactionNotFoundException {

        return mapper.mapToData(service.getTransaction(id));
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeTransaction(@PathVariable("id") String id) throws TransactionNotFoundException {

        service.removeTransaction(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateTransaction(@PathVariable("id") String id, @RequestBody TransactionData transactionData) throws TransactionNotFoundException {

        Transaction transaction =  mapper.mapToEntity(id, transactionData);
        service.updateTransaction(transaction);
    }

    @ExceptionHandler({TransactionNotFoundException.class})
    ResponseEntity handleTransactionNotExistException(TransactionNotFoundException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("6c19dd","Transaction not found", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidTransactionException.class})
    ResponseEntity handleInvalidTransactionException(InvalidTransactionException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("75026b","Invalid transaction", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {

        LOGGER.error(exception.getMessage(), exception);
        ErrorData errorData =  new ErrorData("747f81","Unknown Error", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
