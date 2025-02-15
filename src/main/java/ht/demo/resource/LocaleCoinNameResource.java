package ht.demo.resource;

import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.repository.LocaleCoinNameRepository;
import ht.demo.service.CoinService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LocaleCoinNameResource {

    private final CoinService coinService;
    private final LocaleCoinNameRepository localeCoinNameRepository;

    @PostMapping("/api/locale-coin-name")
    public LocaleCoinName create(
        @NonNull @RequestBody LocaleCoinName localeCoinName
    ) throws BadRequestWithErrorCodeException {
        coinService.exist(
            Optional.of(
                localeCoinName
            )
                .map(LocaleCoinName::getId)
                .map(LocaleCoinNameId::getCharName)
                .orElse("")
        );

        return localeCoinNameRepository.save(localeCoinName);
    }


    @GetMapping("/api/locale-coin-name")
    public List<LocaleCoinName> fetchLocaleCoinName() {
        return localeCoinNameRepository.findAll();
    }

}
