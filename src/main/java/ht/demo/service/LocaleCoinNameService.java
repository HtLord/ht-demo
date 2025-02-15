package ht.demo.service;

import ht.demo.entity.LocaleCoinName;
import ht.demo.error.BadRequestWithErrorCodeException;
import ht.demo.error.LocaleCoinNameCodedError;
import ht.demo.repository.LocaleCoinNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
