package ht.demo.resource;

import ht.demo.dto.coin.NeoCoinDeskDto;
import ht.demo.dto.coin.desk.CoinDeskDtoCollection;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import ht.demo.repository.LocaleCoinNameRepository;
import ht.demo.service.CoinDeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CoinDeskResource {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final CoinDeskService coinDeskService;
    private final LocaleCoinNameRepository localeCoinNameRepository;

    @GetMapping("/coin-desk:proxy")
    public CoinDeskDtoCollection proxy() {
        return coinDeskService.fetch();
    }

    @GetMapping("/coin-desk")
    public List<NeoCoinDeskDto> fetchNeoCoinDesk() {
        return Optional.ofNullable(
                coinDeskService.fetch()
            )
            .map(CoinDeskDtoCollection::getData)
            .orElse(new ArrayList<>())
            .stream()
            .map(coinMapper::convert)
            .toList();
    }

}
