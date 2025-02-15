package ht.demo.dto.coin.desk;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class CoinDeskDto {

    private CoinDeskTimeDto time;
    private String disclaimer;
    private String charName;
    private Map<String, BpiDto> bpi;
}
