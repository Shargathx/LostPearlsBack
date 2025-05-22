package ee.lostpearls.controller.keyword;

import ee.lostpearls.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/location/keywords")
    //todo küsimus kas kasutada hoopis find. nt findAllKeywords v findLocationKeywords
    public void getAllKeywords(@RequestParam Integer locationId) {
        keywordService.getAllKeywords(locationId);
    }
}
