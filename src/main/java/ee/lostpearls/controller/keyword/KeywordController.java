package ee.lostpearls.controller.keyword;

import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import ee.lostpearls.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/location/keywords")
    public List<KeywordInfo> findAllKeywords(@RequestParam Integer locationId) {
        List<KeywordInfo> keywordInfos = keywordService.findALlKeywords(locationId);
        return keywordInfos;

    }
}
