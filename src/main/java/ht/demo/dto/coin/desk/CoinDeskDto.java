package ht.demo.dto.coin.desk;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CoinDeskDto {

    private CoinDeskTimeDto time;
    private String disclaimer;
    private String charName;
    private Map<String, BpiDto> bpi;
}
