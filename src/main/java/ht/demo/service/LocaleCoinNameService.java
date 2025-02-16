package ht.demo.service;

import ht.demo.dto.locale.coin.CharName;
import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.LocaleCoinNameCodedError;
import ht.demo.repository.LocaleCoinNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocaleCoinNameService {

    private final LocaleCoinNameRepository localeCoinNameRepository;

    public void exist(
        LocaleCoinName localeCoinName
    ) throws BadRequestWithErrorCodeException {
        if (!localeCoinNameRepository.existsById(
                localeCoinName.getId()
        )) {
            throw BadRequestWithErrorCodeException.builder()
                .codedError(LocaleCoinNameCodedError.LCN_0001)
                .build();
        }
    }

    public LocaleCoinName put(
        LocaleCoinName localeCoinName
    ) {
        return localeCoinNameRepository.save(
            localeCoinName
        );
    }

    public Map<CharName, String> fetchLocaleCoinNameBy(String locale) {
        return localeCoinNameRepository.findById_LocaleIs(locale)
            .stream()
            .map(x ->
                Map.entry(
                    Optional.ofNullable(x)
                        .map(LocaleCoinName::getId)
                        .map(LocaleCoinNameId::getCharName)
                        .map(y ->
                            CharName.builder()
                                .value(
                                    y
                                )
                                .build()
                        )
                        .orElse(
                            CharName.builder()
                                .value("")
                                .build()
                        ),
                    Optional.ofNullable(x)
                        .map(LocaleCoinName::getName)
                        .orElse("")
                )
            )
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (a, b) -> b
                )
            );
    }
}
