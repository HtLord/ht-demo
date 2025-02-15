package ht.demo.service;

import ht.demo.entity.Coin;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.CoinCodedError;
import ht.demo.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;

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

}
