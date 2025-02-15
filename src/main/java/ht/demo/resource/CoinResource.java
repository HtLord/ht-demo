package ht.demo.resource;

import ht.demo.dto.CoinPatchDto;
import ht.demo.dto.coin.NeoCoinDeskDto;
import ht.demo.dto.coin.desk.DataDto;
import ht.demo.entity.Coin;
import ht.demo.entity.LocaleCoinName;
import ht.demo.error.CoinCodedError;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import ht.demo.repository.LocaleCoinNameRepository;
import ht.demo.service.CoinDeskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CoinResource {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final CoinDeskService coinDeskService;
    private final LocaleCoinNameRepository localeCoinNameRepository;

    @PostMapping("/coins")
    public Coin createCoin(
        @RequestBody Coin coin
    ) {
        return coinRepository.save(coin);
    }

    @GetMapping("/coins")
    public List<Coin> getCoin() {
        return coinRepository.findAll();
    }

    @PatchMapping("/coins")
    @Transactional
    public Coin patchCoin(
        @RequestBody CoinPatchDto dto
    ) throws BadRequestException {
        Coin coin = null;
        coinRepository.save(coin);

        return coin;
    }

    @DeleteMapping("/coins/{name}")
    @Transactional
    public void delete(
        @PathVariable("name") String name
    ) throws BadRequestException {
        var coin =
            coinRepository.findById(name)
                .orElseThrow(() -> new BadRequestException(CoinCodedError.CE_0001.getMessage()));
        coinRepository.delete(coin);
    }

    @GetMapping("/coin-desk:proxy")
    public DataDto proxy() {
        return coinDeskService.fetch();
    }

    @GetMapping("/coin-desk")
    public List<NeoCoinDeskDto> fetchNeoCoinDesk() {
        return Optional.ofNullable(
                coinDeskService.fetch()
            )
            .map(DataDto::getData)
            .orElse(new ArrayList<>())
            .stream()
            .map(coinMapper::convert)
            .toList();
    }

    @GetMapping("/locale-coin-name")
    public List<LocaleCoinName> fetchLocaleCoinName() {
        return localeCoinNameRepository.findAll();
    }

}
