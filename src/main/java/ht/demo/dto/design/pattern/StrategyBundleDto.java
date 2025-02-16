package ht.demo.dto.design.pattern;

import ht.demo.util.strategy.From;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StrategyBundleDto {

    private From from;
    private String user;
}
