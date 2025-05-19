package ee.lostpearls.persistence.user;

import ee.lostpearls.service.LoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)

public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role", target = "roleName")
    LoginResponse toLoginResponse(User user);


}