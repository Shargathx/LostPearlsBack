package ee.lostpearls.service;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.controller.location.dto.LocationResponse;
import ee.lostpearls.infrastructure.error.Error;
import ee.lostpearls.infrastructure.exception.DataNotFoundException;
import ee.lostpearls.infrastructure.exception.DuplicateLocationException;
import ee.lostpearls.infrastructure.exception.ForeignKeyNotFoundException;
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

    public Integer addLocation(Integer userId, LocationInfo locationInfo) {
        validateLocationIsAvailable(locationInfo.getLocationName(), locationInfo.getCountyId());
        Location location = createAndSaveLocation(userId, locationInfo);
        return location.getId();
    }

    public LocationInfo findLocation(Integer locationId) {
        Location location = locationRepository.findLocationBy(locationId, LOCATION_ADDED.getCode())
                .orElseThrow(() -> new DataNotFoundException(Error.NO_LOCATIONS_FOUND.getMessage(), Error.NO_LOCATIONS_FOUND.getErrorCode()));
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


    public List<LocationResponse> findAllLocationsByUserId(Integer userId) {
        List<Location> locations = locationRepository.findLocationsByUserIdAndStatus(userId, LOCATION_ADDED.getCode());
        if (locations.isEmpty()) {
            throw new DataNotFoundException(Error.NO_LOCATIONS_FOUND.getMessage(), Error.NO_LOCATIONS_FOUND.getErrorCode());
        }
        return locationMapper.toLocationResponses(locations);
    }


    public void updateLocation(Integer locationId, LocationDto locationDto) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId ", locationId));
        County county = countyRepository.findCountyById(locationDto.getCountyId()).orElseThrow(() -> new PrimaryKeyNotFoundException("countyId ", locationDto.getCountyId()));
        location.setCounty(county);
        locationMapper.partialUpdate(location, locationDto);
        locationRepository.save(location);
    }


    public void removeLocation(Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId ", locationId));
        location.setStatus(LocationStatus.LOCATION_DELETED.getCode());
        locationRepository.save(location);
    }

    private Location createAndSaveLocation(Integer userId, LocationInfo locationInfo) {
        County county = countyRepository.findCountyById(locationInfo.getCountyId())
                .orElseThrow(() -> new ForeignKeyNotFoundException("countyId ", locationInfo.getCountyId()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ForeignKeyNotFoundException("userId", userId));

        Location location = locationMapper.toLocation(locationInfo);
        location.setUser(user);
        location.setCounty(county);
        location.setDateAdded(LocalDate.now());
        locationRepository.save(location);
        return location;
    }

    private void validateLocationIsAvailable(String locationName, Integer countyId) {
        boolean locationExists = locationRepository.locationExistsByLocationNameAndCountyId(locationName, countyId);
        if (locationExists) {
            throw new DuplicateLocationException(Error.LOCATION_UNAVAILABLE.getMessage(), Error.LOCATION_UNAVAILABLE.getErrorCode());
        }
    }


}

