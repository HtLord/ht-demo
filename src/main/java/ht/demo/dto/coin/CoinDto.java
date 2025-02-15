package ht.demo.dto.coin;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoinDto {

    private String time;
    private String disclaimer;
    private String charName;
    private String localeName;
    private List<BpiDto> bpis;
}
