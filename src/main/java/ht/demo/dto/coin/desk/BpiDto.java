package ht.demo.dto.coin.desk;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BpiDto {

    private String code;
    private String symbol;
    private float rate;
    private String description;
    @JsonProperty("rate_float")
    private String rateFloat;
}
