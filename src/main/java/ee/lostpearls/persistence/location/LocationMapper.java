package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.controller.location.dto.LocationResponse;
import ee.lostpearls.controller.location.dto.RandomLocationResponse;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.status.LocationStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, imports = LocationStatus.class)
public interface LocationMapper {

    @Mapping(source = "longitude", target = "longfield")
    @Mapping(source = "latitude", target = "latfield")
    @Mapping(expression = "java(LocationStatus.LOCATION_ADDED.getCode())", target = "status")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    @Mapping(source = "locationName", target = "name")
    Location toLocation(LocationDto locationDto);


    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "longfield", target = "longitude")
    @Mapping(source = "latfield", target = "latitude")
    @Mapping(source = "teaser", target = "teaser")
    @Mapping(source = "extendedInfo", target = "extendedInfo")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "answer", target = "answer")
    LocationDto toLocationDto(Location location);

    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latfield", target = "latitude")
    @Mapping(source = "longfield", target = "longitude")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    @Mapping(source = "teaser", target = "teaser")
    @Mapping(source = "extendedInfo", target = "extendedInfo")
    @Mapping(source = "question", target = "question")
    @Mapping(source = "answer", target = "answer")
    LocationInfo toLocationInfo(Location location);

    List<LocationInfo> toLocationInfos(List<Location> locations);


    @Mapping(expression = "java(LocationStatus.LOCATION_ADDED.getCode())", target = "status")
    @Mapping(source = "countyId", target = "county.id")
    @Mapping(source = "latitude", target = "latfield")
    @Mapping(source = "longitude", target = "longfield")
    @Mapping(source = "locationName", target = "name")
    Location partialUpdate(@MappingTarget Location location, LocationDto locationDto);


    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "longfield", target = "longitude")
    @Mapping(source = "latfield", target = "latitude")
    @Mapping(source = "zoomlevel", target = "zoomlevel")
    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "id", target = "locationId")
    LocationResponse toLocationResponse(Location location);

    List<LocationResponse> toLocationResponses(List<Location> locations);


    @Mapping(source = "name", target = "locationName")
    @Mapping(source = "latfield", target = "latitude")
    @Mapping(source = "longfield", target = "longitude")
    @Mapping(source = "dateAdded", target = "dateAdded")
    RandomLocationResponse toRandomLocationResponse(Location location);

    List<RandomLocationResponse> toRandomLocationResponses(List<Location> locations);


}