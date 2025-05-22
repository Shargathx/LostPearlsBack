package ee.lostpearls.service;

import ee.lostpearls.status.GameStatus;
import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.county.CountyRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationMapper;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor

public class LocationService {

    private final LocationMapper locationMapper;
    private final UserRepository userRepository;
    private final CountyRepository countyRepository;
    private final LocationRepository locationRepository;

    public void addLocation(Integer userId, LocationDto locationDto) {
        Location location = locationMapper.toLocation(locationDto);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("user Id ", userId));
        County county = countyRepository.findById(locationDto.getCountyId())
                .orElseThrow(() -> new PrimaryKeyNotFoundException("county Id ", locationDto.getCountyId()));



        location.setUser(user);
        location.setCounty(county);

        location.setStatus(GameStatus.ACTIVE.getCode());
        location.setDateAdded(LocalDate.now());

        locationRepository.save(location);


    }


    public void getAtmLocation() {

    }

}
