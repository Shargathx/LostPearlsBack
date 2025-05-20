package ee.lostpearls.persistence.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link ee.lostpearls.persistence.role.Role}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(max = 255)
    private String name;
}