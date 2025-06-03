package ee.lostpearls.controller.county;

import ee.lostpearls.controller.county.dto.CountyInfo;
import ee.lostpearls.controller.county.dto.CountyResponse;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.service.CountyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountyController {

    private final CountyService countyService;

    @GetMapping("/counties")
    @Operation(summary = "Leiab kõik county asukohad vastavalt countyId-le")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public List<CountyInfo> getAllCounties() {
        List<CountyInfo> countyInfos = countyService.getAllCounties();
        return countyInfos;
    }

    @GetMapping("/county/{countyId}")
    @Operation(summary = "Leiab ainult ühe county vastavalt countyId-le, kasutatakse selleks, et genereerida random mäng")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public CountyResponse getCounty(@PathVariable Integer countyId) {
        return countyService.getCounty(countyId);
    }
    @GetMapping("/county/location/{locationId}")
    @Operation(summary = "Leiab locationId abil üles county")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public CountyInfo getCountyByLocation(@PathVariable Integer locationId) {
        return countyService.findCountyByLocationId(locationId);
    }
}
