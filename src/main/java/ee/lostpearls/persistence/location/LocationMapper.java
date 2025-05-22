package ee.lostpearls.persistence.location;

import ee.lostpearls.persistence.county.CountyMapper;
import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.persistence.user.UserMapper;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, CountyMapper.class})
public interface LocationMapper {

    // to Entity
    Location toLocation(LocationDto locationDto);


}