package ht.demo.service;

import ht.demo.dto.coin.CoinDto;
import ht.demo.entity.Coin;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.CoinCodedError;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinService {

    private final CoinRepository coinRepository;
    private final CoinMapper coinMapper;
    private final LocaleCoinNameService localeCoinNameService;

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

    @Transactional(readOnly = true)
    public List<CoinDto> fetchAllBy(String locale) {
        var nameDict =
            localeCoinNameService.fetchLocaleCoinNameBy(
                locale
            );
        return coinRepository.findAll()
            .stream()
            .sorted(
                Comparator.comparing(Coin::getCharName)
            )
            .map(x ->
                coinMapper.toCoinDto(
                    x,
                    nameDict
                )
            )
            .toList();
    }

}
