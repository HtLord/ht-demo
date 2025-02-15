package ht.demo.dto.coin.desk;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CoinDeskDtoCollection {

    List<CoinDeskDto> data;
}
