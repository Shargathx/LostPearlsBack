package ee.lostpearls.controller.profile;


import ee.lostpearls.controller.profile.dto.UserProfile;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    @Operation(summary = "Kasutaja andmete leidmine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "X", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public UserProfile getUserProfile(Integer userId) {
        UserProfile userProfile = userService.getUserProfile(userId);
        return userProfile;
    }





        // todo PATCH passwordi muutimine





}

