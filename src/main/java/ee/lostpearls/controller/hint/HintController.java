package ee.lostpearls.controller.hint;

import ee.lostpearls.controller.hint.dto.HintInfo;
import ee.lostpearls.service.HintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HintController {

    private final HintService hintService;

    @GetMapping("/location")
    public List<HintInfo> findAllHints(@RequestParam Integer locationId) {
        List<HintInfo> hintInfos = hintService.findAllHints(locationId);
        return hintInfos;
    }

}
