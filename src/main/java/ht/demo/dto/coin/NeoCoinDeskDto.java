package ht.demo.dto.coin;

import ht.demo.dto.coin.desk.BpiDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class NeoCoinDeskDto {

    private String time;
    private String disclaimer;
    private String charName;
    private Map<String, BpiDto> bpi;
}
