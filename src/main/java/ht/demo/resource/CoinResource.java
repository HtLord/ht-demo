package ht.demo.resource;

import ht.demo.dto.coin.CoinDto;
import ht.demo.service.CoinService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CoinResource {

    private final CoinService coinService;

    @GetMapping("/api/coins")
    @Parameter(
        name = "Accept-Language",
        in = ParameterIn.HEADER,
        example = "zh-tw"
    )
    public List<CoinDto> fetch(HttpServletRequest request) {
        return coinService.fetchAllBy(
            request.getLocale()
                .toString()
        );
    }

}
