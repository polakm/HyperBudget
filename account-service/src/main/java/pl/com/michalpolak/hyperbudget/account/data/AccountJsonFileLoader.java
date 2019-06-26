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

class AccountJsonFileLoader implements InitialDataLoader {

    private final InputStream dataStream;

    AccountJsonFileLoader(InputStream dataStream) {
        this.dataStream = dataStream;
    }

    AccountJsonFileLoader(String path) throws IOException {
        this.dataStream = new ClassPathResource(path).getInputStream();
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
