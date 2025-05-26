package ee.lostpearls.service;

import ee.lostpearls.controller.keyword.dto.KeywordDto;
import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import ee.lostpearls.infrastructure.exception.ForeignKeyNotFoundException;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.answer.Answer;
import ee.lostpearls.persistence.answer.AnswerMapper;
import ee.lostpearls.persistence.answer.AnswerRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordService {


    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final LocationRepository locationRepository;

    public List<KeywordInfo> findAllKeywords(Integer locationId) {
        List<Answer> keywordsBy = answerRepository.findKeywordsBy(locationId);
        List<KeywordInfo> keywordInfos = answerMapper.toKeywordInfos(keywordsBy);
        return keywordInfos;
    }



    public void addKeyword(KeywordDto keywordDto) {
        Location location = locationRepository.findById(keywordDto.getLocationId())
                .orElseThrow(() -> new ForeignKeyNotFoundException("locationId", keywordDto.getLocationId()));
        Answer answer = answerMapper.toAnswer(keywordDto);
        answer.setLocation(location);
        answerRepository.save(answer);
    }

    public void deleteKeyword(Integer answerId) {
        Answer keyword = answerRepository.findById(answerId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("answerId", answerId));
        answerRepository.delete(keyword);
    }
}


