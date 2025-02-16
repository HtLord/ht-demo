package ht.demo.service;

import ht.demo.dto.coin.CoinDto;
import ht.demo.entity.Coin;
import ht.demo.error.CoinCodedError;
import ht.demo.mapper.CoinMapper;
import ht.demo.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
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

    public void exist(String charName) throws BadRequestException {
        coinRepository.findById(charName)
            .orElseThrow(CoinCodedError.CE_0001::generateBadRequestException);
    }

    public void saveOrUpdate(List<Coin> coins) {
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
