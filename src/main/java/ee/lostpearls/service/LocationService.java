package ee.lostpearls.service;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.infrastructure.error.Error;
import ee.lostpearls.infrastructure.exception.DataNotFoundException;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.county.CountyRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationMapper;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import ee.lostpearls.status.LocationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static ee.lostpearls.status.LocationStatus.LOCATION_ADDED;

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
        location.setDateAdded(LocalDate.now());

        locationRepository.save(location);
    }

    public LocationInfo findLocation(Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId ", locationId));
        if (!location.getStatus().equals(LOCATION_ADDED.getCode())) {
            throw new PrimaryKeyNotFoundException("locationId ", locationId);
        }
        return locationMapper.toLocationInfo(location);
    }


    public List<LocationInfo> findAllLocations() {
        List<Location> locations = locationRepository.findByStatus(LOCATION_ADDED.getCode());
        if (locations.isEmpty()) {
            throw new DataNotFoundException(Error.NO_LOCATIONS_FOUND.getMessage(), Error.NO_LOCATIONS_FOUND.getErrorCode());
        }
        List<LocationInfo> locationDtos = locationMapper.toLocationInfos(locations);
        return locationDtos;
    }


    public Location updateLocation(Integer locationId, LocationDto locationDto) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId ", locationId));
        County county = countyRepository.findById(locationDto.getCountyId()).orElseThrow(() -> new PrimaryKeyNotFoundException("countyId ", locationDto.getCountyId()));
        location.setCounty(county);
        locationMapper.partialUpdate(location, locationDto);
        locationRepository.save(location);
        return location;
    }


    public void removeLocation(Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId ", locationId));
        location.setStatus(LocationStatus.LOCATION_DELETED.getCode());
        locationRepository.save(location);
    }

}

