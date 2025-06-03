package ee.lostpearls.controller.login;

import ee.lostpearls.controller.login.dto.LoginResponse;
import ee.lostpearls.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    @Operation(summary = "Lubab kasutajal sisse logida / handlib kasutaja sisselogimist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public LoginResponse login(@RequestParam String loginName, @RequestParam String password) {
        LoginResponse login = loginService.login(loginName, password);
        return login;
    }
}
