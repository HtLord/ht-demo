package ht.demo.service;

import ht.demo.config.HtConfig;
import ht.demo.dto.crypto.CryptoDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BCryptService {

    private final HtConfig htConfig;
    private String salt;
    private final Map<String, String> storedHashes = new HashMap<>();

    @PostConstruct
    void generateSalt() {
        salt =
            BCrypt.gensalt(
                htConfig.getSecurity()
                    .getLogRounds()
            );
    }

    public void generateAndStoreHash(
        CryptoDto dto
    ) {
        storedHashes.put(
            dto.getUser(),
            BCrypt.hashpw(
                dto.getPw(),
                salt
            )
        );
    }

    public boolean validate(
        CryptoDto dto
    ) {
        return BCrypt.checkpw(
            dto.getPw(),
            storedHashes.getOrDefault(
                dto.getUser(),
                ""
            )
        );
    }
}
