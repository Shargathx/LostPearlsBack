package ee.lostpearls.controller.profile.dto;

import ee.lostpearls.persistence.user.User;
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
public class UserProfile implements Serializable {
    private String username;
    private String email;
}