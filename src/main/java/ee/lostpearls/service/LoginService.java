package ee.lostpearls.service;

import ee.lostpearls.status.UserStatus;
import ee.lostpearls.controller.login.dto.LoginResponse;
import ee.lostpearls.infrastructure.exception.ForbiddenException;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public LoginResponse login(String loginName, String password) {
        User user = userRepository.findUserBy(loginName, loginName, password, UserStatus.ACTIVE.getCode())
                .orElseThrow(() -> new ForbiddenException("Vale kasutajanimi või paroool", 111));

        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        return loginResponse;
    }
}