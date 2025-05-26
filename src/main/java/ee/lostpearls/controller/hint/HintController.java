package ee.lostpearls.controller.hint;

import ee.lostpearls.controller.hint.dto.HintInfo;
import ee.lostpearls.service.HintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HintController {

    private final HintService hintService;

    @GetMapping("/hints")
    public List<HintInfo> findAllHints(@RequestParam Integer locationId) {
        List<HintInfo> hintInfos = hintService.findAllHints(locationId);
        return hintInfos;
    }

    //todo ei tööta
    @PostMapping("/hint")
    public void addHint(Integer locationId, @RequestParam HintInfo hintInfo) {
        hintService.addHint(locationId, hintInfo);
    }

    @DeleteMapping("/hint")
    public void deleteHint(Integer hintId) {
        hintService.deleteHint(hintId);
    }
}
