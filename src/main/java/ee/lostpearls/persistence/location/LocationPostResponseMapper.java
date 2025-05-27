package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationPostResponse;
import ee.lostpearls.controller.location.dto.LocationPostResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LocationPostResponseMapper {


    @Mapping(source = "id", target = "id")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "latField", target = "latitude")
    @Mapping(source = "teaser", target = "teaser")
    @Mapping(source = "extendedInfo", target = "extendedInfo")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "answer", target = "answer")
    LocationPostResponse toResponse(Location location);





    LocationPostResponseDto toDto(LocationPostResponse locationPostResponse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LocationPostResponse partialUpdate(LocationPostResponseDto locationPostResponseDto, @MappingTarget LocationPostResponse locationPostResponse);
}