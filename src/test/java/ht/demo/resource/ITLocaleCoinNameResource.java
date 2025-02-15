package ht.demo.resource;

import ht.demo.entity.Coin;
import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.repository.CoinRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITLocaleCoinNameResource {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoSpyBean
    CoinRepository coinRepository;

    private final String LocaleCoinCharName = "test-charname";
    private final String LocaleCoinLocale = "test-locale";
    private final String LocaleCoinNameName = "test-name";

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(coinRepository);
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
    void create() throws Exception {
        var data =
            this.restTemplate
                .postForEntity(
                    "http://localhost:" + port + "/api/locale-coin-name",
                    LocaleCoinName.builder()
                        .id(
                            LocaleCoinNameId.builder()
                                .locale(LocaleCoinLocale)
                                .charName(LocaleCoinCharName)
                                .build()
                        )
                        .name(LocaleCoinNameName)
                        .build(),
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
}
