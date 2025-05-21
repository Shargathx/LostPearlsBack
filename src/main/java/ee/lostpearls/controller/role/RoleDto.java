package ee.lostpearls.controller.role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ee.lostpearls.persistence.role.Role}
 */
@Value
public class RoleDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String name;
}