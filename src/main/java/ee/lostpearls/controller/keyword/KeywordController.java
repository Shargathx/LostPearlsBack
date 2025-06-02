package ee.lostpearls.controller.keyword;

import ee.lostpearls.controller.keyword.dto.KeywordDto;
import ee.lostpearls.controller.keyword.dto.KeywordInfo;
import ee.lostpearls.service.KeywordService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/keywords")
    @Operation(summary = "annab vastuste variandid eri vormingutes mille baasi teha võrdlust mängul sisestatud vastusega")
    public List<KeywordInfo> findAllKeywords(@RequestParam Integer locationId) {
        List<KeywordInfo> keywordInfos = keywordService.findAllKeywords(locationId);
        return keywordInfos;
    }

    @PostMapping ("/keyword")
    @Operation(summary = "võrdluse aluseks oleva vastuse variandi sisestamine")
    public void addKeyword(@RequestBody KeywordDto keywordDto) {
        keywordService.addKeyword(keywordDto);
    }

    @DeleteMapping("/keyword")
    @Operation(summary = "vastuse variandi kustutamine")
    public void deleteKeyword(@RequestParam Integer keywordId) {
        keywordService.deleteKeyword(keywordId);
    }

    @GetMapping("/keyword")
    @Operation(summary = "tagastab keywordi et nt kontrollida millist keywordi kustutad")
    public KeywordInfo getKeyword (@RequestParam Integer keywordId){
        KeywordInfo keyword = keywordService.getKeyword(keywordId);
        return keyword;
    }
}
