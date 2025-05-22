package ee.lostpearls.persistence.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.persistence.county.CountyMapper;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.status.GameStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, CountyMapper.class}, imports = GameStatus.class)
public interface LocationMapper {

    // to Entity
    @Mapping(source = "longitude", target = "longField")
    @Mapping(source = "latitude", target = "lat")
 //   @Mapping(source = "answer", target = "answer")
    @Mapping(expression = "java(GameStatus.ACTIVE.getCode())", target = "status")
    Location toLocation(LocationDto locationDto);
}