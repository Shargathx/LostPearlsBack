package ee.lostpearls.controller.game;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Integer addGame(@RequestParam Integer userId, @RequestParam Integer locationId) {
        Game game = gameService.addGame(userId, locationId);
        return game.getId();
    }

    @PatchMapping("/game/started")
    @Operation(summary = "Uuendab m2ngu tabelit vastavalt kaugele m2nguga j6utud on.",
            description = """
                    Sisuliselt saab m2ng uueneda kaks korda.Esiteks kui vajutatakse Start, millega muutub saatus ja luuakse start_time. 
                    Teiseks kui sisestatakse 6ige vastus ja m2ng on completed.""")
    public void startGame(@RequestParam Integer gameId) {
        gameService.startGame(gameId);
    }

}
