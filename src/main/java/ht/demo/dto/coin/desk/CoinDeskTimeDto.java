package ht.demo.dto.coin.desk;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class CoinDeskTimeDto {

    private String updated;
    private Instant updatedISO;
    private String updateduk;
}
