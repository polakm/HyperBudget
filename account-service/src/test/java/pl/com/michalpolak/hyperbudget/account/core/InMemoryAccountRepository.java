package pl.com.michalpolak.hyperbudget.account.core;

import pl.com.michalpolak.hyperbudget.account.core.api.Account;
import pl.com.michalpolak.hyperbudget.account.core.spi.AccountRepository;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> storage;

    private InMemoryAccountRepository(Map<String, Account> initialData) {
        this.storage = new ConcurrentHashMap<>(initialData);
    }

    private InMemoryAccountRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    static InMemoryAccountRepository of(Map<String, Account> data) {
        return new InMemoryAccountRepository(data);
    }

    static AccountRepository empty() {

        return new InMemoryAccountRepository();
    }

    @Override
    public Account save(Account account) {

        this.storage.put(account.getId(), account);
        return account;
    }

    @Override
    public Set<Account> getAll() {

        return new HashSet(this.storage.values());
    }

    @Override
    public void remove(String id) {

        this.storage.remove(id);
    }

    @Override
    public Account findById(String id) {

        return this.storage.get(id);
    }

    @Override
    public Account update(Account account) {

        this.storage.put(account.getId(), account);
        return account;
    }
}
