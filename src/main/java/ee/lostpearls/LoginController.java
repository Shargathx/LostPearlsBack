package ee.lostpearls;

import ee.lostpearls.service.LoginResponse;
import ee.lostpearls.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public LoginResponse login(@RequestParam String loginName, @RequestParam String password) {
        LoginResponse login = loginService.login(loginName, password);
        return login;
    }
}
