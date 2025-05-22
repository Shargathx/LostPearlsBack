package ee.lostpearls.controller;

import ee.lostpearls.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;


    public void addLocation(@RequestBody LocationDto locationDto){

    }
}
