package pl.com.michalpolak.hyperbudget.transaction.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.michalpolak.hyperbudget.transaction.core.api.Transaction;
import pl.com.michalpolak.hyperbudget.transaction.core.api.TransactionService;

@RestController
public class TransactionRestController {

    private TransactionService service;

    public TransactionRestController(@Autowired TransactionService service) {
        this.service = service;
    }

    @PostMapping
    Transaction addTranasaction(@RequestBody Transaction transaction){
        return service.addTransaction(transaction);
    }

    @GetMapping
    String test(){
        return "It's work!";
    }

}
