package ht.demo.mapper;

import ht.demo.dto.coin.CoinDto;
import ht.demo.dto.coin.NeoCoinDeskDto;
import ht.demo.dto.coin.desk.CoinDeskDto;
import ht.demo.dto.locale.coin.CharName;
import ht.demo.entity.Coin;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.Named;
import org.mapstruct.NullValueMappingStrategy;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG,
    uses = {BpiMapper.class}
)
public interface CoinMapper {

    default DateTimeFormatter getNeoCoinDeskTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }

    @Mapping(
        target = "time",
        source = "time.updatedISO",
        qualifiedByName = "convertCoinDeskTimeToNeoCoinDeskTime"
    )
    NeoCoinDeskDto convert(CoinDeskDto source);

    @Named("convertCoinDeskTimeToNeoCoinDeskTime")
    default String convertCoinDeskTimeToNeoCoinDeskTime(Instant time) throws ParseException {
        return this.getNeoCoinDeskTimeFormatter()
            .withZone(ZoneId.from(ZoneOffset.UTC))
            .format(time);
    }

    @Mapping(target = "bpis", source = "bpi")
    Coin toEntity(CoinDeskDto source, @Context String charName);

    @Mapping(
        target = "time",
        source = "lastModifiedDate",
        qualifiedByName = "convertCoinDeskTimeToNeoCoinDeskTime"
    )
    @Mapping(
        target = "localeName",
        source = "charName",
        qualifiedByName = "attachCoinNameFromDict"
    )
    CoinDto toCoinDto(Coin source, @Context Map<CharName, String> nameDict);

    @Named("attachCoinNameFromDict")
    default String attachCoinNameFromDict(
        String charName,
        @Context Map<CharName, String> nameDict
    ) {
        return nameDict.getOrDefault(
            CharName.builder()
                .value(charName)
                .build(),
            ""
        );
    }
}
