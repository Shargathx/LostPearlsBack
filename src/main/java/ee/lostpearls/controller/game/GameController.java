package ee.lostpearls.controller.game;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game")
    @Operation(summary = "Tagastab m2ngu vastavalt valitud asukoha p6hjal")
    public GameInfo findGame(@RequestParam Integer gameId) {
        return gameService.findGame(gameId);
    }

    @PostMapping("/game")
    @Operation(summary = "Loob asukoha p6hiselt m2ngu, mida on v6imalik hiljem valida ja alustada")
    public gameId createGame(@RequestBody GameInfo gameInfo) {
        gameService.createAndSaveGame(gameInfo);
        return gameId;
    }

}
