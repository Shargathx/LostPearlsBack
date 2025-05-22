package ee.lostpearls.persistence.county;

import ee.lostpearls.controller.county.dto.CountyInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountyMapper {


    CountyInfo toCountyDto(County county);

}