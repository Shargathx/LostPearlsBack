package ee.lostpearls.service;

import ee.lostpearls.controller.keyword.dto.KeywordDto;
import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import ee.lostpearls.infrastructure.exception.ForeignKeyNotFoundException;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.keyword.Keyword;
import ee.lostpearls.persistence.keyword.KeywordMapper;
import ee.lostpearls.persistence.keyword.KeywordRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {


    private final KeywordRepository answerRepository;
    private final KeywordMapper keywordMapper;
    private final LocationRepository locationRepository;

    public List<KeywordInfo> findAllKeywords(Integer locationId) {
        List<Keyword> keywordsBy = answerRepository.findKeywordsBy(locationId);
        List<KeywordInfo> keywordInfos = keywordMapper.toKeywordInfos(keywordsBy);
        return keywordInfos;
    }



    public void addKeyword(KeywordDto keywordDto) {
        Location location = locationRepository.findById(keywordDto.getLocationId())
                .orElseThrow(() -> new ForeignKeyNotFoundException("locationId", keywordDto.getLocationId()));
        Keyword keyword = keywordMapper.toKeyword(keywordDto);
        keyword.setLocation(location);
        answerRepository.save(keyword);
    }

    public void deleteKeyword(Integer answerId) {
        Keyword keyword = answerRepository.findById(answerId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("answerId", answerId));
        answerRepository.delete(keyword);
    }
}


