package ht.demo.resource;

import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.mapper.CoinMapper;
import ht.demo.service.CoinDeskService;
import ht.demo.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinResource {

    private final CoinMapper coinMapper;
    private final CoinDeskService coinDeskService;
    private final CoinService coinService;

    @GetMapping("/api/coins")
    public void test() throws BadRequestWithErrorCodeException {
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
