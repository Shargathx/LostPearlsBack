package ee.lostpearls.controller.location;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.controller.location.dto.LocationResponse;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class LocationController {

    private final LocationService locationService;
    private final LocationRepository locationRepository;

    @PostMapping("/location")
    @Operation(summary = "Uue asukoha lisamine")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "480", description = "Selline asukoht on juba olemas", content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addLocation(@RequestParam Integer userId, @RequestBody LocationInfo locationInfo) {
        locationService.addLocation(userId, locationInfo);
    }

//    @GetMapping("/location/check-duplicate")
//    public boolean checkDuplicate(@RequestParam String locationName, @RequestParam Integer countyId, @RequestParam Double latitude, @RequestParam Double longitude) {
//        return locationService.checkDuplicate(locationName, countyId, latitude, longitude);
//    }


    @GetMapping("/location/{locationId}")
    public LocationInfo findLocation(@PathVariable Integer locationId) {
        return locationService.findLocation(locationId);
    }


    @GetMapping("/locations")
    public List<LocationInfo> findAllLocations() {
        return locationService.findAllLocations();
    }


    @GetMapping("/locations/all")
    public List<LocationResponse> findAllLocationsByUserId(@RequestParam Integer userId) {
        return locationService.findAllLocationsByUserId(userId);
    }


    @PutMapping("/location")
    public void updateLocation(@RequestParam Integer locationId, @RequestBody LocationDto locationDto) {
        locationService.updateLocation(locationId, locationDto);

    }


    @DeleteMapping("/location")
    public void removeLocation(@RequestParam Integer locationId) {
        locationService.removeLocation(locationId);
    }


}
