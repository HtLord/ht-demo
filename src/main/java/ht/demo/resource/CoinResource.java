package ht.demo.resource;

import ht.demo.dto.CoinPatchDto;
import ht.demo.entity.Coin;
import ht.demo.error.CoinError;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CoinResource {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;

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
        var coin =
            coinRepository.findById(dto.getName())
                .orElseThrow(() -> new BadRequestException(CoinError.CE_0001.getMessage()));
        coinMapper.patch(coin, dto);
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
                .orElseThrow(() -> new BadRequestException(CoinError.CE_0001.getMessage()));
        coinRepository.delete(coin);
    }
}
