package ee.lostpearls.controller.county;

import ee.lostpearls.controller.county.dto.CountyInfo;
import ee.lostpearls.controller.county.dto.CountyResponse;
import ee.lostpearls.service.CountyService;
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
    public List<CountyInfo> getAllCounties() {
        List<CountyInfo> countyInfos = countyService.getAllCounties();
        return countyInfos;
    }

    @GetMapping("/county/{countyId}")
    public CountyResponse getCounty(@PathVariable Integer countyId) {
        return countyService.getCounty(countyId);
    }
    @GetMapping("/county/location/{locationId}")
    public CountyInfo getCountyByLocation(@PathVariable Integer locationId) {
        return countyService.findCountyByLocationId(locationId);
    }


}
