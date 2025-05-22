package ee.lostpearls.service;

import ee.lostpearls.persistence.answer.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final AnswerRepository answerRepository;

    public void getAllKeywords() {

    }
}
