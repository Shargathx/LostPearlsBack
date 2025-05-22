package ee.lostpearls.controller.login.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;
    private String roleName;
}
