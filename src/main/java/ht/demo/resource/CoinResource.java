package ht.demo.resource;

import ht.demo.entity.Coin;
import ht.demo.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class CoinResource {

    private final CoinRepository coinRepository;

    public CoinResource(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @GetMapping("/coins")
    public List<Coin> getCoin() {
        return coinRepository.findAll();
    }
}
