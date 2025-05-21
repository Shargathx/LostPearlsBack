package ee.lostpearls.service;

import ee.lostpearls.infrastructure.error.Error;
import ee.lostpearls.infrastructure.exception.ForbiddenException;
import ee.lostpearls.persistence.user.UserDto;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;


    public void addUser(UserDto userDto) {
        boolean userExists = userRepository.userExistsBy(userDto.getUsername(), userDto.getEmail());
        if (userExists) {
            throw new ForbiddenException(Error.NAME_OR_EMAIL_UNAVAILABLE.getMessage(), Error.NAME_OR_EMAIL_UNAVAILABLE.getErrorCode());
        }

        createUser(userDto)
    }
}
