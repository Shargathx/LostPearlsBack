package ee.lostpearls.service;

import ee.lostpearls.controller.hint.dto.HintDto;
import ee.lostpearls.controller.hint.dto.HintInfo;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.hint.Hint;
import ee.lostpearls.persistence.hint.HintMapper;
import ee.lostpearls.persistence.hint.HintRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HintService {


    private final HintRepository hintRepository;
    private final HintMapper hintMapper;
    private final LocationRepository locationRepository;

    public List<HintInfo> findAllHints(Integer locationId) {
        List<Hint> hintsBy = hintRepository.findLocationsBy(locationId);
        List<HintInfo> hintInfos = hintMapper.toHintInfos(hintsBy);
        return hintInfos;
    }

    public void addHint(HintDto hintDto) {

        Location location = locationRepository.findById(hintDto.getLocationId())
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId", hintDto.getLocationId()));
        Hint hint = hintMapper.toHint(hintDto);
        hint.setLocation(location);
        hintRepository.save(hint);

    }

    public void deleteHint(Integer hintId) {
        Hint hint = hintRepository.findById(hintId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("hintId", hintId));
        hintRepository.delete(hint);
    }
}

