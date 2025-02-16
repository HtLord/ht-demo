package ht.demo.resource;

import ht.demo.dto.crypto.CryptoDto;
import ht.demo.service.BCryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CryptoResource {

    private final BCryptService bCryptService;

    @PostMapping("/api/crypto:store")
    public void storePw(
        @RequestBody CryptoDto dto
    ) {
        bCryptService.generateAndStoreHash(dto);
    }

    @PostMapping("/api/crypto:validate")
    public boolean validatePw(
        @RequestBody CryptoDto dto
    ) {
        return bCryptService.validate(dto);
    }
}
