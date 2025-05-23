package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.status.GameStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class}, imports = GameStatus.class)
public interface LocationMapper {




//    @Mapping(source = "answer", target = "answer")
  //  @Mapping(source = "teaser", target = "teaser")
    @Mapping(source = "longitude", target = "longField")
    @Mapping(source = "latitude", target = "lat")
    @Mapping(constant = "A", target = "status")
    Location toLocation(LocationDto locationDto);



    @Mapping(source = "county.name", target = "countyName")
    @Mapping(source = "county.id", target = "countyId")
    @Mapping(source = "longField", target = "longitude")
    @Mapping(source = "lat", target = "latitude")
    LocationInfo toLocationInfo(Location location);


}