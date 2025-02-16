package ht.demo.service;

import ht.demo.dto.crypto.CryptoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestBCryptService {

    @Autowired
    BCryptService bCryptService;

    @Test
    void testMatchingPassword() {
        var dto =
            CryptoDto.builder()
                .user("hello")
                .pw("world")
                .build();
        bCryptService.generateAndStoreHash(
            dto
        );
        Assertions.assertTrue(bCryptService.validate(dto));
    }

    @Test
    void testMissMatchingPassword() {
        var dto =
            CryptoDto.builder()
                .user("hello")
                .pw("world")
                .build();
        bCryptService.generateAndStoreHash(
            dto
        );
        var hijack =
            CryptoDto.builder()
                .user("hello")
                .pw("yeah")
                .build();
        Assertions.assertFalse(bCryptService.validate(hijack));
    }
}
