package ht.demo.mapper;

import ht.demo.dto.coin.desk.CoinDeskBpiDto;
import ht.demo.entity.Bpi;
import ht.demo.entity.BpiId;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG
)
public interface BpiMapper {

    default List<Bpi> mapBpiMapToList(Map<String, CoinDeskBpiDto> bpiMap, @Context String chartName) {
        if (bpiMap == null) return null;
        return bpiMap.values()
            .stream()
            .map(x ->
                Bpi.builder()
                    .id(
                        BpiId.builder()
                            .charName(chartName)
                            .code(x.getCode())
                            .build()
                    )
                    .description(x.getDescription())
                    .rate(x.getRateFloat())
                    .symbol(x.getSymbol())
                    .build()
            )
            .collect(Collectors.toList());
    }

    @Mapping(source = "rateFloat", target = "rate")
    @Mapping(target = "coin", ignore = true)
    Bpi toEntity(CoinDeskBpiDto dto, @Context String chartName);
}
