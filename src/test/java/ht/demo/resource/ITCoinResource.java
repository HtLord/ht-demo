package ht.demo.resource;

import ht.demo.dto.coin.CoinDto;
import ht.demo.entity.Coin;
import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.repository.CoinRepository;
import ht.demo.repository.LocaleCoinNameRepository;
import lombok.AllArgsConstructor;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCoinResource {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoSpyBean
    CoinRepository coinRepository;
    @MockitoSpyBean
    LocaleCoinNameRepository localeCoinNameRepository;

    static Instant now = Instant.now();
    String locale = "zh_TW";

    @AllArgsConstructor
    enum TestData {
        b("b", "hello", "world"),
        A("A", "jp", "あ"),
        c("c", "zh_TW", "西"),
        ;

        private final String charName;
        private final String locale;
        private final String localeName;

        public Coin generateCoin() {
            return Coin.builder()
                .charName(charName)
                .lastModifiedDate(now)
                .build();
        }

        public LocaleCoinName generateLocalCoinName() {
            return LocaleCoinName.builder()
                .id(
                    LocaleCoinNameId.builder()
                        .locale(locale)
                        .charName(charName)
                        .build()
                )
                .name(localeName)
                .build();
        }
    }

    void mockCoin() {
        Mockito.doReturn(
                Arrays.stream(
                        TestData.values()
                    )
                    .map(TestData::generateCoin)
                    .toList()
            )
            .when(coinRepository)
            .findAll();
    }

    void mockLocaleCoinName(TestData acceptLanguageTarget) {
        Mockito.doReturn(
                List.of(
                    acceptLanguageTarget.generateLocalCoinName()
                )
            )
            .when(localeCoinNameRepository)
            .findById_LocaleIs(
                Mockito.anyString()
            );
    }

    @BeforeEach
    void init() {
        this.mockCoin();
        this.mockLocaleCoinName(TestData.c);
    }

    @Test
    void testFetch() {
        var headers = new HttpHeaders();
        headers.add(
            HttpHeaders.ACCEPT_LANGUAGE,
            locale
        );
        var req =
            new HttpEntity<>(
                headers
            );
        var res =
            restTemplate.exchange(
                "http://localhost:" + port + "/api/coins",
                HttpMethod.GET,
                req,
                new ParameterizedTypeReference<List<CoinDto>>() {
                }
            );
        var resultMap =
            res.getBody()
                .stream()
                .map(x ->
                    Map.entry(x.getCharName(), x.getLocaleName())
                )
                .collect(
                    Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                    )
                );
        Assertions.assertEquals(
            "",
            resultMap.get(TestData.A.charName)
        );
        Assertions.assertEquals(
            "",
            resultMap.get(TestData.b.charName)
        );
        Assertions.assertEquals(
            TestData.c.localeName,
            resultMap.get(TestData.c.charName)
        );
    }
}
