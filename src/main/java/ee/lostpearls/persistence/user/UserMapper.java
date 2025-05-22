package ee.lostpearls.persistence.user;


import ee.lostpearls.controller.registration.dto.UserDto;
import ee.lostpearls.status.UserStatus;
import ee.lostpearls.controller.login.dto.LoginResponse;
import ee.lostpearls.controller.profile.dto.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {UserStatus.class})
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "role.name", target = "roleName")
    LoginResponse toLoginResponse(User user);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(expression = "java(UserStatus.ACTIVE.getCode())", target = "status")
    User toUser(UserDto userDto);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    UserProfile toUserProfile(User user);


}