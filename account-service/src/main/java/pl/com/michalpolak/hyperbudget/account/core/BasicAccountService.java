package pl.com.michalpolak.hyperbudget.account.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.michalpolak.hyperbudget.account.core.api.*;
import pl.com.michalpolak.hyperbudget.account.core.spi.AccountRepository;

import java.util.Collections;
import java.util.Set;


class BasicAccountService implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAccountService.class);

    private final AccountRepository accountRepository;
    private final AccountValidator validator;

    BasicAccountService(AccountRepository accountRepository, AccountValidator validator) {
        this.accountRepository = accountRepository;
        this.validator = validator;
    }

    @Override
    public Account addAccount(Account account) throws InvalidAccountException {

        LOGGER.info("Add new account - Account ID: {}", account.getId());
        validator.validate(account);
        Account result = accountRepository.save(account);
        LOGGER.info("New account has added - Account ID: {}", account.getId());
        return result;
    }

    @Override
    public void removeAccount(AccountId id) throws AccountNotFoundException {

        LOGGER.info("Remove account - Account ID: {}", id);
        this.getAccount(id);
        this.accountRepository.remove(id.toString());
        LOGGER.info("Account has removed - Account ID: {}", id);
    }

    @Override
    public Account getAccount(AccountId id) throws AccountNotFoundException {

        Account result = this.accountRepository.findById(id.toString());
        if (result == null) {
            throw new AccountNotFoundException(id);
        }
        return result;
    }

    @Override
    public Set<Account> allAccounts() {
        return Collections.unmodifiableSet(this.accountRepository.getAll());
    }

    @Override
    public Account updateAccount(Account account) throws AccountNotFoundException {

        LOGGER.info("Update account - Account ID: {}", account.getId());
        this.getAccount(account.getId());
        Account result = this.accountRepository.update(account);
        LOGGER.info("Account has updated - Account ID: {}", account.getId());
        return result;
    }
}

