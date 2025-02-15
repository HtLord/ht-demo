package ht.demo.resource;

import ht.demo.config.HtConfig;
import ht.demo.dto.coin.desk.CoinDeskDto;
import ht.demo.dto.coin.desk.CoinDeskDtoCollection;
import ht.demo.dto.coin.desk.CoinDeskTimeDto;
import ht.demo.service.CoinDeskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntTestCoinResources {

    @MockitoBean
    private CoinDeskService coinDeskService;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private HtConfig htConfig;

    private Instant now = Instant.now();

    CoinDeskDtoCollection buildFakeData() {
        return CoinDeskDtoCollection
            .builder()
            .data(
                Stream.of(
                        CoinDeskDto.builder()
                            .time(
                                CoinDeskTimeDto.builder()
                                    .updatedISO(now)
                                    .build()
                            )
                            .build()
                    )
                    .toList()
            )
            .build();
    }

    @Test
    void testProxyToCoinDesk() throws Exception {

        Mockito.doReturn(
                this.buildFakeData()
            )
            .when(coinDeskService)
            .fetch();
        var data =
            this.restTemplate
                .getForObject(
                    "http://localhost:" + port + "/coin-desk:proxy",
                    CoinDeskDtoCollection.class
                );
        Assertions.assertEquals(
            1,
            Optional.ofNullable(
                    data
                )
                .map(CoinDeskDtoCollection::getData)
                .map(List::size)
                .orElse(0)
        );
    }

}
