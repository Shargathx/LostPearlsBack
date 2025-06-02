package ee.lostpearls.controller.game;

import ee.lostpearls.controller.game.dto.GameCompletedInfo;
import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.controller.game.dto.GamesInProgressInfo;
import ee.lostpearls.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game")
    @Operation(summary = "Tagastab m2ngu info vastavalt valitud asukoha p6hjal")
    public GameInfo findGame(@RequestParam Integer gameId) {
        return gameService.findGame(gameId);
    }

    @PostMapping("/game")
    @Operation(summary = "Loob asukoha p6hiselt m2ngu, mida on v6imalik hiljem valida ja alustada")
    public void addGame(@RequestParam Integer countyId, @RequestParam Integer userId) {
        gameService.addGame(countyId, userId);
    }

    @PatchMapping("/game/started")
    @Operation(summary = "Uuendab m2ngu tabelit vastavalt kaugele m2nguga j6utud on.",
            description = """
                    Sisuliselt saab m2ng uueneda kaks korda.Esiteks kui vajutatakse Start, millega muutub saatus ja luuakse start_time. 
                    Teiseks kui sisestatakse 6ige vastus ja m2ng on completed.""")
    public void startGame(@RequestParam Integer gameId) {
        gameService.startGame(gameId);
    }

    @GetMapping("/games-in-progress-info")
    public GamesInProgressInfo getGamesInProgressInfo(@RequestParam Integer userId) {
        return gameService.getGamesInProgressInfo(userId);
    }

    @GetMapping("/games/complete")
    @Operation(summary = "Tagastab listi m2ngitud m2ngudest userId p6hjal")
    public List<GameCompletedInfo> getUserCompletedGames(Integer userId) {
        return gameService.getUserCompletedGames(userId);
    }

    @PatchMapping("/game/completed")
    @Operation(summary = "Uuendab m2ngu vastavalt kui m2ng on l6petatud",
            description = "Lisab tabelisse m2ngu l6petamise aja, muudab staatuse ja arvutab punktid")
    public void completeGame (@RequestParam Integer gameId) {
        gameService.completeGame(gameId);
    }

}
