package ee.lostpearls.service;

import ee.lostpearls.controller.profile.dto.UserProfile;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserMapper;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserProfile getUserProfile(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
        UserProfile userProfile = userMapper.toUserProfile(user);
        return userProfile;
    }


}
