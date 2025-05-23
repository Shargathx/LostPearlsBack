package ee.lostpearls.service;

import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import ee.lostpearls.persistence.answer.Answer;
import ee.lostpearls.persistence.answer.AnswerMapper;
import ee.lostpearls.persistence.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {


    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    public List<KeywordInfo> getAllKeywords(Integer locationId) {
        List<Answer> keywordsBy = answerRepository.findKeywordsBy(locationId);
        List<KeywordInfo> keywordInfos = answerMapper.toKeywordInfos(keywordsBy);
        return keywordInfos;
    }
}

