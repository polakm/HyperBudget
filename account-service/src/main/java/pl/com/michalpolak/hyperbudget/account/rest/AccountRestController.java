package pl.com.michalpolak.hyperbudget.account.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.api.AccountNotFoundException;
import pl.com.michalpolak.hyperbudget.account.core.api.AccountService;
import pl.com.michalpolak.hyperbudget.account.core.api.InvalidAccountException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/accounts", headers = {"X-API-Version=1"})
public class AccountRestController {


    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    private final AccountService service;
    private final AccountDataMapper mapper;

    @Autowired
    public AccountRestController(AccountService service, AccountDataMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @RequestMapping(method = RequestMethod.POST)
    AccountData addAccount(@RequestBody AccountData accountData) throws InvalidAccountException {

        Account account = mapper.mapToEntity(accountData);
        service.addAccount(account);
        return mapper.mapToData(account);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<AccountData> accountList() {

        Set<Account> accounts = service.allAccounts();
        return mapper.mapToDataList(accounts);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    AccountData getAccount(@PathVariable("id") String id) throws AccountNotFoundException {

        Account account = service.getAccount(id);
        return mapper.mapToData(account);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    void removeAccount(@PathVariable("id") String id) throws AccountNotFoundException {

        service.removeAccount(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    void updateAccount(@PathVariable("id") String id, @RequestBody AccountData accountData) throws AccountNotFoundException {

        Account account = mapper.mapToEntity(id, accountData);
        service.updateAccount(account);
    }

    @ExceptionHandler({AccountNotFoundException.class})
    ResponseEntity handleAccountNotExistException(AccountNotFoundException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData = ErrorData.of("e2fe0a", "Account not found", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidAccountException.class})
    ResponseEntity handleInvalidAccountException(InvalidAccountException exception, WebRequest request) {

        LOGGER.warn(exception.getMessage(), exception);
        ErrorData errorData = ErrorData.of("f059ab", "Invalid account", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    ResponseEntity handleUnknownException(Exception exception, WebRequest request) {

        LOGGER.error(exception.getMessage(), exception);
        ErrorData errorData = ErrorData.of("3e1b0b", "Unknown Error", exception.getMessage());
        return new ResponseEntity(errorData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
