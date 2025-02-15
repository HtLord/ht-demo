package ht.demo.dto.coin;

import ht.demo.dto.coin.desk.CoinDeskBpiDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NeoCoinDeskDto {

    private String time;
    private String disclaimer;
    private String charName;
    private String localeName;
    private Map<String, CoinDeskBpiDto> bpi;
}
