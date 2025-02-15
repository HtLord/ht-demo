package ht.demo.resource;

import ht.demo.dto.coin.CoinDto;
import ht.demo.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoinResource {

    private final CoinService coinService;

    @GetMapping("/api/coins")
    public List<CoinDto> fetch() {
        return coinService.fetchAll();
    }

}
