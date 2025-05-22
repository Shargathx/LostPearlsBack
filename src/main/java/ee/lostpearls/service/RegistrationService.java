package ee.lostpearls.service;


import ee.lostpearls.controller.registration.dto.UserDto;
import ee.lostpearls.infrastructure.error.Error;
import ee.lostpearls.infrastructure.exception.ForbiddenException;
import ee.lostpearls.persistence.role.Role;
import ee.lostpearls.persistence.role.RoleRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    public static final int ROLE_CUSTOMER = 2;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;


    public void addUser(UserDto userDto) {
        boolean userExists = userRepository.userExistsBy(userDto.getUsername(), userDto.getEmail());
        if (userExists) {
            throw new ForbiddenException(Error.NAME_OR_EMAIL_UNAVAILABLE.getMessage(), Error.NAME_OR_EMAIL_UNAVAILABLE.getErrorCode());
        }
        Role role = roleRepository.findById(ROLE_CUSTOMER).get();

        User user = userMapper.toUser(userDto);
        user.setRole(role);
        userRepository.save(user);
    }
}
