package ht.demo.schedule;

import ht.demo.scheduler.CoinDeskScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest
public class TestCoinDeskScheduler {

    @MockitoSpyBean
    CoinDeskScheduler coinDeskScheduler;

    @Test
    public void reportCurrentTime() {
//        await().atMost(Durations.TEN_SECONDS)
//            .untilAsserted(() -> {
//                verify(coinDeskScheduler, atLeast(2))
//                    .pullCoinDeskAndUpdate();
//            });
    }
}
