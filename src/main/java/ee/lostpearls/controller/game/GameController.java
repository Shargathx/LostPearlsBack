package ee.lostpearls.controller.game;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game")
    @Operation(summary = "Tagastab m2ngu vastavalt valitud asukoha p6hjal")
    public GameInfo findGame(@RequestParam Integer gameId) {
        GameInfo gameInfo = gameService.findGame(gameId);
        return gameInfo;
    }
}
