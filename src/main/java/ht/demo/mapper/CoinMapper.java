package ht.demo.mapper;

import ht.demo.dto.CoinPatchDto;
import ht.demo.entity.Coin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG
)
public interface CoinMapper {

    @Mapping(target = "exchangeRate", source = "exchangeRate")
    void patch(@MappingTarget Coin origin, CoinPatchDto extra);
}
