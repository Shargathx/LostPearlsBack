package ee.lostpearls.controller.hint;

import ee.lostpearls.controller.hint.dto.HintDto;
import ee.lostpearls.controller.hint.dto.HintInfo;
import ee.lostpearls.service.HintService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HintController {

    private final HintService hintService;

    @GetMapping("/hints")
    @Operation(summary = "sisestatud vihjete kuvamine")
    public List<HintInfo> findAllHints(@RequestParam Integer locationId) {
        List<HintInfo> hintInfos = hintService.findAllHints(locationId);
        return hintInfos;
    }

    @PostMapping("/hint")
    @Operation(summary = "vihje sisestamine")
    public void addHint(@RequestBody HintDto hintDto) {
        hintService.addHint(hintDto);
    }

    @DeleteMapping("/hint")
    @Operation(summary = "vihje kustutamine")
    public void deleteHint(Integer hintId) {
        hintService.deleteHint(hintId);
    }
}
