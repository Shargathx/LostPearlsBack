package ee.lostpearls.service;

import ee.lostpearls.controller.location.dto.LocationDto;
import ee.lostpearls.controller.location.dto.LocationInfo;
import ee.lostpearls.controller.location.dto.LocationResponse;
import ee.lostpearls.controller.location.dto.RandomLocationResponse;
import ee.lostpearls.infrastructure.error.Error;
import ee.lostpearls.infrastructure.exception.DataNotFoundException;
import ee.lostpearls.infrastructure.exception.DuplicateLocationException;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.county.County;
import ee.lostpearls.persistence.county.CountyRepository;
import ee.lostpearls.persistence.game.GameRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationMapper;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import ee.lostpearls.status.LocationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static ee.lostpearls.status.LocationStatus.LOCATION_ADDED;

@Service
@RequiredArgsConstructor

public class LocationService {

    private final LocationMapper locationMapper;
    private final UserRepository userRepository;
    private final CountyRepository countyRepository;
    private final LocationRepository locationRepository;
    private final GameRepository gameRepository;

    public LocationDto addLocation(Integer userId, LocationInfo locationCreateDto) {
        Location location = locationMapper.toLocation(locationCreateDto);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("user Id ", userId));
        County county = countyRepository.findCountyById(locationCreateDto.getCountyId())
                .orElseThrow(() -> new PrimaryKeyNotFoundException("county Id ", locationCreateDto.getCountyId()));

        boolean locationExists = locationRepository.locationExistsByNameAndCounty(locationCreateDto.getLocationName(), county.getId());
        if (locationExists) {
            throw new DuplicateLocationException();
        }

        location.setUser(user);
        location.setCounty(county);
        location.setDateAdded(LocalDate.now());

        Location savedLocation = locationRepository.save(location);

        return locationMapper.toLocationDto(savedLocation);
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


    public List<LocationResponse> findAllLocationsByUserId(Integer userId) {
        List<Location> locations = locationRepository.findLocationByUserIdAndStatus(userId, LOCATION_ADDED.getCode());
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

    public RandomLocationResponse getRandomCountyLocation(Integer countyId, Integer userId) {
        List<Location> randomLocationByCounty = locationRepository.findRandomLocationByCounty(countyId, LOCATION_ADDED.getCode());

        if (randomLocationByCounty.isEmpty()) {
            throw new PrimaryKeyNotFoundException("countyId", countyId);
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new PrimaryKeyNotFoundException("userId", userId);
        }

        List<Integer> locationIdsBy = gameRepository.findPlayedLocationIdsBy(userId);
        List<Integer> locationCreatedBy = locationRepository.findLocationCreatedByUser(userId);

        Set<Integer> excludedIds = new HashSet<>();
        if (locationIdsBy != null) excludedIds.addAll(locationIdsBy);
        if (locationCreatedBy != null) excludedIds.addAll(locationCreatedBy);

        List<Location> validLocations = randomLocationByCounty.stream().filter(location -> !excludedIds.contains((location.getId()))).toList();

        if (validLocations.isEmpty()) {
            throw new PrimaryKeyNotFoundException("countyId", countyId);
        }

        Location location = validLocations.get(new Random().nextInt(validLocations.size()));
        return locationMapper.toRandomLocationResponse(location);
    }


// HELPER METHODS

    private double round(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }


}

