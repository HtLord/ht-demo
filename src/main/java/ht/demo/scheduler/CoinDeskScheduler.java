package ht.demo.scheduler;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CoinDeskScheduler {

    @Scheduled(fixedRate = 10000)
    public void pullCoinDeskAndUpdate() {
        throw new NotImplementedException();
    }
}
