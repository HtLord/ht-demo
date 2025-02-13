package ht.demo.resource;

import ht.demo.entity.Coin;
import ht.demo.repository.CoinRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoinResource {

    private final CoinRepository coinRepository;

    public CoinResource(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

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
}
