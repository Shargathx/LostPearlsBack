package ee.lostpearls.persistence.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestDto implements Serializable {
    private Integer id;
    @NotNull
    private RoleDto role;
    @NotNull
    @Size(max = 20)
    private String username;
    @NotNull
    @Size(max = 20)
    private String password;
    @NotNull
    @Size(max = 255)
    private String email;
    @NotNull
    private String status;
}