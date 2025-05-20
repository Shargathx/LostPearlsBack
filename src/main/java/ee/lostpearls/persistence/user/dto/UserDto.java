package ee.lostpearls.persistence.user.dto;

import ee.lostpearls.persistence.role.dto.RoleDto;
import ee.lostpearls.persistence.user.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    RoleDto role;
    @NotNull
    @Size(max = 20)
    String username;
    @NotNull
    @Size(max = 20)
    String password;
    @NotNull
    @Size(max = 255)
    String email;
    @NotNull
    String status;
}