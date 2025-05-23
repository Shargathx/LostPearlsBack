package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.status.GameStatus;
import ee.lostpearls.status.LocationStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, imports = LocationStatus.class)
public interface LocationMapper {




    @Mapping(source = "longitude", target = "longField")
    @Mapping(source = "latitude", target = "lat")
    @Mapping(expression = "java(LocationStatus.LOCATION_ADDED.getCode())", target = "status")
    Location toLocation(LocationDto locationDto);



    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "lat", target = "latitude")
    LocationInfo toLocationInfo(Location location);


}