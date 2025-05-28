package ee.lostpearls.persistence.county;

import ee.lostpearls.controller.county.dto.CountyInfo;
import ee.lostpearls.controller.county.dto.CountyResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountyMapper {
    County toEntity(CountyInfo countyInfo);

    @Mapping(source = "id", target = "countyId")
    @Mapping(source = "name", target = "countyName")
    CountyInfo toCountyInfo(County county);

    List<CountyInfo> toCountyInfos(List<County> county);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    County partialUpdate(CountyInfo countyInfo, @MappingTarget County county);


    @Mapping(source = "id", target = "countyId")
    @Mapping(source = "latfield", target = "latitude")
    @Mapping(source = "longfield", target = "longitude")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    CountyResponse toCountyResponse(County county);
}