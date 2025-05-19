package ee.lostpearls.service;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;
    private Integer roleId;
    private String roleName;
}
