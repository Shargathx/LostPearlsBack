package ee.lostpearls.controller.location;

import ee.lostpearls.controller.location.dto.*;
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
    @Operation(summary = "Uue asukoha lisamine, teenus tagastab lisatud asukoha 'locationId'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Selline asukoht juba eksisteerib (errorCode 333)", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "406", description = "Foreign key not found: '{fieldName}' with value: '{fieldValue} (errorCode 666)", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public Integer addLocation(@RequestParam Integer userId, @RequestBody LocationInfo locationInfo) {
        return locationService.addLocation(userId, locationInfo);

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
    @Operation(summary = "Leiab kõik asukohad vastavalt userId-le")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Selline asukoht juba eksisteerib (errorCode 333)", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "406", description = "Foreign key not found: '{fieldName}' with value: '{fieldValue} (errorCode 666)", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public List<LocationResponse> findAllLocationsByUserId(@RequestParam Integer userId) {
        return locationService.findAllLocationsByUserId(userId);
    }


    @PutMapping("/location/{locationId}")
    @Operation(summary = "Lubab kasutajal asukoha infot muuta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Selline asukoht juba eksisteerib (errorCode 333)", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "406", description = "Foreign key not found: '{fieldName}' with value: '{fieldValue} (errorCode 666)", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void updateLocation(@PathVariable Integer locationId, @RequestBody LocationDto locationDto) {
        locationService.updateLocation(locationId, locationDto);

    }

    @Operation(summary = "Kustutab asukoha (Status A -> D)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Selline asukoht juba eksisteerib (errorCode 333)", content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "406", description = "Foreign key not found: '{fieldName}' with value: '{fieldValue} (errorCode 666)", content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/location")
    public void removeLocation(@RequestParam Integer locationId) {
        locationService.removeLocation(locationId);
    }


}
