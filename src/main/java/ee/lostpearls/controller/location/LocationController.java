package ee.lostpearls.controller.location;

import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class LocationController {

    private final LocationService locationService;

    @PostMapping("/location")
    @Operation(summary = "Uue asukoha lisamine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "480", description = "Selline asukoht on juba olemas", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addLocation(@RequestParam Integer userId, @RequestBody LocationInfo locationInfo) {
        locationService.addLocation(userId, locationInfo);
    }


    @GetMapping("/location/{locationId}")
    public LocationInfo findLocation(@PathVariable Integer locationId) {
        return locationService.findLocation(locationId);
    }


    @GetMapping("/locations")
    public List<LocationInfo> findAllLocations() {
        return locationService.findAllLocations();
    }




/*
    @PutMapping("/location/{locationId}")
    public void PatchLocation patchLocation(@RequestParam Integer userId, @RequestBody LocationInfo locationInfo) {

    }

 */

}
