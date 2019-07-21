package pl.com.michalpolak.hyperbudget.account.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import pl.com.michalpolak.hyperbudget.account.core.api.Account;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class AccountJsonFileLoader implements InitialDataLoader {

    private final InputStream dataStream;

    private AccountJsonFileLoader(String path) throws IOException {
        this.dataStream = new ClassPathResource(path).getInputStream();
    }

    static AccountJsonFileLoader fromJsonFile(String path) throws IOException {
       return new AccountJsonFileLoader(path);
    }

    public List<Account> loadAsList() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return (List<Account>) objectMapper.readValue(dataStream, new TypeReference<List<Account>>() {
        });

    }

    public Map<String, Account> loadAsMap() throws IOException {
        return loadAsList().stream().collect(Collectors.toMap(Account::getId, item -> item));
    }
}
