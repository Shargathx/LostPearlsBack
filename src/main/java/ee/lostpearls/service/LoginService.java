package ee.lostpearls.service;

import ee.lostpearls.Status;
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
        User user = userRepository.findUserBy(loginName, loginName, password, Status.ACTIVE.getCode())
                .orElseThrow(() -> new ForbiddenException("Vale kasutajanimi või paroool", 111));

        // sama asi mis all aga mapperiga

        LoginResponse loginResponse = userMapper.toLoginResponse(user);
        return loginResponse;
/*
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(user.getId());
        loginResponse.setRoleName(user.getRole().getName());
        return loginResponse;
        */
    }
}