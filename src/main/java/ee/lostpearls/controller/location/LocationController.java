package ee.lostpearls.controller.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LocationController {

    private final LocationService locationService;

    @PostMapping("/location")
    @Operation(summary = "Uue asukoha lisamine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "480", description = "Selline asukoht on juba olemas", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addLocation(@RequestParam Integer userId, @RequestBody LocationDto locationDto) {
        locationService.addLocation(userId,locationDto);
    }

}
