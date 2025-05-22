package ee.lostpearls.controller;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;
    private String roleName;
}
