package ee.lostpearls.service;

import ee.lostpearls.Status;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LoginService {

    private final UserRepository userRepository;

    public LoginResponse login(String username, String password) {
        User user = userRepository.findUserBy(username, password, Status.ACTIVE.getCode());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setRoleId(user.getRole().getId());
        loginResponse.setRoleName(user.getRole().getName()); // dunno if this is needed
        return loginResponse;
    }
}