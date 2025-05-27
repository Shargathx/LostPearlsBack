package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.controller.location.dto.LocationResponse;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.status.LocationStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, imports = LocationStatus.class)
public interface LocationMapper {

    @Mapping(source = "longitude", target = "longField")
    @Mapping(source = "latitude", target = "latField")
    @Mapping(expression = "java(LocationStatus.LOCATION_ADDED.getCode())", target = "status")
    Location toLocation(LocationDto locationDto);


    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "locationName", target = "locationName")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "latField", target = "latitude")
    @Mapping(source = "teaser", target = "teaser")
    @Mapping(source = "extendedInfo", target = "extendedInfo")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "answer", target = "answer")
    LocationDto toLocationDto(Location location);


    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "latField", target = "latitude")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    LocationInfo toLocationInfo(Location location);
    List<LocationInfo> toLocationInfos(List<Location> locations);


    @Mapping(expression = "java(LocationStatus.LOCATION_ADDED.getCode())", target = "status")
    @Mapping(source = "countyId", target = "county.id")
    @Mapping(source = "latitude", target = "latField")
    @Mapping(source = "longitude", target = "longField")
    Location partialUpdate(@MappingTarget Location location, LocationDto locationDto);


    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "latField", target = "latitude")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    LocationResponse toLocationResponse(Location location);
    List<LocationResponse> toLocationResponses(List<Location> locations);


}