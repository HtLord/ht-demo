package ht.demo.resource;

import ht.demo.entity.LocaleCoinName;
import ht.demo.entity.LocaleCoinNameId;
import ht.demo.error.LocaleCoinNameCodedError;
import ht.demo.repository.LocaleCoinNameRepository;
import ht.demo.service.CoinService;
import ht.demo.service.LocaleCoinNameService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LocaleCoinNameResource {

    private final CoinService coinService;
    private final LocaleCoinNameService localeCoinNameService;
    private final LocaleCoinNameRepository localeCoinNameRepository;

    @PostMapping("/api/locale-coin-name")
    public LocaleCoinName create(
        @NonNull @RequestBody LocaleCoinName localeCoinName
    ) throws BadRequestException {
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

    @PutMapping("/api/locale-coin-name")
    public LocaleCoinName put(
        @NonNull @RequestBody LocaleCoinName localeCoinName
    ) throws BadRequestException {
        localeCoinNameService.exist(localeCoinName);
        return localeCoinNameService.put(localeCoinName);
    }

    @DeleteMapping("/api/locale-coin-name")
    public void delete(
        @NonNull @RequestBody LocaleCoinName localeCoinName
    ) throws BadRequestException {
        var data =
            localeCoinNameRepository.findById(
                localeCoinName.getId()
            );
        if (data.isEmpty()) {
            throw LocaleCoinNameCodedError.LCN_0001.generateBadRequestException();
        }
        localeCoinNameRepository.delete(data.get());
    }

}
