package ht.demo.service;

import ht.demo.dto.coin.CoinDto;
import ht.demo.entity.Coin;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.CoinCodedError;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import ht.demo.repository.LocaleCoinNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final LocaleCoinNameRepository localeCoinNameRepository;

    public void exist(String charName) throws BadRequestWithErrorCodeException {
        coinRepository.findById(charName)
            .orElseThrow(() ->
                BadRequestWithErrorCodeException.builder()
                    .codedError(CoinCodedError.CE_0001)
                    .build()
            );
    }

    public void saveOrUpdate(List<Coin> coins) throws BadRequestWithErrorCodeException {
        coinRepository.saveAll(coins);
    }

    public List<CoinDto> fetchAll() {
        return coinRepository.findAll()
            .stream()
            .sorted(
                Comparator.comparing(Coin::getCharName)
            )
            .map(coinMapper::toCoinDto)
            .toList();
    }

}
