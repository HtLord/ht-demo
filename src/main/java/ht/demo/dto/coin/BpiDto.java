package ht.demo.dto.coin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BpiDto {

    private String code;
    private String symbol;
    private Float rate;
    private String description;
}
