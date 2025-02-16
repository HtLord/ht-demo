package ht.demo.service;

import ht.demo.config.HtConfig;
import ht.demo.dto.coin.desk.CoinDeskDtoCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CoinDeskService {

    private final HtConfig htConfig;

    public CoinDeskDtoCollection fetch() {
        var defaultClient =
            RestClient.create();
        return defaultClient.get()
            .uri(
                URI.create(
                    "%s%s".formatted(
                        htConfig.getService()
                            .getData()
                            .getHost(),
                        htConfig.getService()
                            .getData()
                            .getEndpoint()
                    )
                )
            )
            .retrieve()
            .body(CoinDeskDtoCollection.class);
    }
}
