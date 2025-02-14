package ht.demo.service;

import ht.demo.dto.coin.desk.DataDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
public class CoinDeskService {

    public DataDto fetch() {
        var defaultClient =
            RestClient.create();
        return defaultClient.get()
                .uri(URI.create("http://localhost:3000/"))
                .retrieve()
                .body(DataDto.class);
    }
}
