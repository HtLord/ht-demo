package ht.demo.resource;

import ht.demo.error.BadRequestWithErrorCodeException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoinResource {


    @GetMapping("/api/coins")
    public void fetch() throws BadRequestWithErrorCodeException {
    }

}
