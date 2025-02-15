package ht.demo.resource;

import ht.demo.entity.Coin;
import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.repository.CoinRepository;
import ht.demo.repository.LocaleCoinNameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITLocaleCoinNameResource {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoSpyBean
    CoinRepository coinRepository;
    @MockitoSpyBean
    LocaleCoinNameRepository localeCoinNameRepository;

    private final String LocaleCoinCharName = "test-charname";
    private final String LocaleCoinLocale = "test-locale";
    private final String LocaleCoinNameName = "test-name";

    @BeforeEach
    void init() {
        Mockito.doReturn(
                Optional.of(
                    Coin.builder()
                        .charName(LocaleCoinCharName)
                        .build()
                )
            )
            .when(coinRepository)
            .findById(Mockito.anyString());
    }

    @Test
    void create() {
        var data =
            this.restTemplate
                .exchange(
                    "http://localhost:" + port + "/api/locale-coin-name",
                    HttpMethod.POST,
                    new HttpEntity<>(
                        LocaleCoinName.builder()
                            .id(
                                LocaleCoinNameId.builder()
                                    .locale(LocaleCoinLocale)
                                    .charName(LocaleCoinCharName)
                                    .build()
                            )
                            .name(LocaleCoinNameName)
                            .build()
                    ),
                    LocaleCoinName.class
                );
        Assertions.assertEquals(
            LocaleCoinCharName,
            data.getBody().getId().getCharName()
        );
        Assertions.assertEquals(
            LocaleCoinLocale,
            data.getBody().getId().getLocale()
        );
        Assertions.assertEquals(
            LocaleCoinNameName,
            data.getBody().getName()
        );
    }

    @Test
    void read() {
        Mockito.doReturn(
                List.of(
                    LocaleCoinName.builder()
                        .id(
                            LocaleCoinNameId.builder()
                                .charName(LocaleCoinCharName)
                                .locale(LocaleCoinLocale)
                                .build()
                        )
                        .name(LocaleCoinNameName)
                        .build()
                )
            )
            .when(localeCoinNameRepository)
            .findAll();
        var data =
            this.restTemplate
                .exchange(
                    "http://localhost:" + port + "/api/locale-coin-name",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LocaleCoinName>>() {
                    }
                );
        var localeCoin =
            data.getBody().get(0);
        Assertions.assertEquals(
            LocaleCoinCharName,
            localeCoin.getId().getCharName()
        );
        Assertions.assertEquals(
            LocaleCoinLocale,
            localeCoin.getId().getLocale()
        );
        Assertions.assertEquals(
            LocaleCoinNameName,
            localeCoin.getName()
        );
    }

    @Test
    void put() {
        this.restTemplate
            .exchange(
                "http://localhost:" + port + "/api/locale-coin-name",
                HttpMethod.POST,
                new HttpEntity<>(
                    LocaleCoinName.builder()
                        .id(
                            LocaleCoinNameId.builder()
                                .locale(LocaleCoinLocale)
                                .charName(LocaleCoinCharName)
                                .build()
                        )
                        .name(LocaleCoinNameName)
                        .build()
                ),
                LocaleCoinName.class
            );
        var data =
            this.restTemplate
                .exchange(
                    "http://localhost:" + port + "/api/locale-coin-name",
                    HttpMethod.PUT,
                    new HttpEntity<>(
                        LocaleCoinName.builder()
                            .id(
                                LocaleCoinNameId.builder()
                                    .locale(LocaleCoinLocale)
                                    .charName(LocaleCoinCharName)
                                    .build()
                            )
                            .name("hello-world")
                            .build()
                    ),
                    LocaleCoinName.class
                );
        Assertions.assertEquals(
            LocaleCoinCharName,
            data.getBody().getId().getCharName()
        );
        Assertions.assertEquals(
            LocaleCoinLocale,
            data.getBody().getId().getLocale()
        );
        Assertions.assertEquals(
            "hello-world",
            data.getBody().getName()
        );
    }

}
