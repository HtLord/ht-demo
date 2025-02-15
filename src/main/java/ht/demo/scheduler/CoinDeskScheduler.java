package ht.demo.scheduler;

import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.mapper.CoinMapper;
import ht.demo.service.CoinDeskService;
import ht.demo.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoinDeskScheduler {

    private final CoinMapper coinMapper;
    private final CoinDeskService coinDeskService;
    private final CoinService coinService;

    @Scheduled(fixedRate = 10000)
    public void syncWithCoinDesk() throws BadRequestWithErrorCodeException {
        var data =
            coinDeskService.fetch();
        var entities =
            data.getData()
                .stream()
                .map(x -> coinMapper.toEntity(x, x.getCharName()))
                .toList();
        coinService.saveOrUpdate(entities);
    }
}
