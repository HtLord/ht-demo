package ht.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ht.demo.entity.Coin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(
    value = {
        "lastModifiedDate",
    }
)
public class CoinPatchDto extends Coin {
}
