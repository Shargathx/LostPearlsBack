package ee.lostpearls.controller;

import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.persistence.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    @Operation(summary = "Uue kasutaja registeerimine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403", description = "Sellise nime v6i emailiga kasutaja on juba olemas", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addUser(@RequestBody UserDto userDto) {
        registrationService.addUser(userDto);
    }






}
