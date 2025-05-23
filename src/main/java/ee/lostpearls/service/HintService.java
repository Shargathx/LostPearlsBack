package ee.lostpearls.service;

import ee.lostpearls.controller.hint.dto.HintInfo;
import ee.lostpearls.persistence.hint.Hint;
import ee.lostpearls.persistence.hint.HintMapper;
import ee.lostpearls.persistence.hint.HintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HintService {


    private final HintRepository hintRepository;
    private final HintMapper hintMapper;

    public List<HintInfo> findAllHints(Integer locationId) {
        List<Hint> hintsBy = hintRepository.findLocationsBy(locationId);
        List<HintInfo> hintInfos = hintMapper.toHintInfos(hintsBy);
        return hintInfos;
    }
}

