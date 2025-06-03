package ee.lostpearls.controller.game;

import ee.lostpearls.controller.game.dto.GameCompletedInfo;
import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.controller.game.dto.GamesInProgressInfo;
import ee.lostpearls.infrastructure.error.ApiError;
import ee.lostpearls.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/game")
    @Operation(summary = "Tagastab mängu info vastavalt valitud asukoha põhjal")
    public GameInfo findGame(@RequestParam Integer gameId) {
        return gameService.findGame(gameId);
    }

    @PostMapping("/game")
    @Operation(summary = "Loob asukoha põhiselt m2ngu, mida on võimalik hiljem valida ja alustada")
    public void addGame(@RequestParam Integer countyId, @RequestParam Integer userId) {
        gameService.addGame(countyId, userId);
    }

    @PatchMapping("/game/started")
    @Operation(summary = "Uuendab mängu tabelit vastavalt kaugele mänguga jõutud on.",
            description = """
                    Sisuliselt saab mäng uueneda kaks korda.Esiteks kui vajutatakse Start, millega muutub saatus ja luuakse start_time. 
                    Teiseks kui sisestatakse õige vastus ja mäng on completed.""")
    public void startGame(@RequestParam Integer gameId) {
        gameService.startGame(gameId);
    }

    @GetMapping("/games-in-progress-info")
    @Operation(summary = "Tagastab kasutaja pooleliolevad mängud (Game_Status GA ja GS)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public GamesInProgressInfo getGamesInProgressInfo(@RequestParam Integer userId) {
        return gameService.getGamesInProgressInfo(userId);
    }

    @GetMapping("/games/complete")
    @Operation(summary = "Tagastab listi mängitud mängudest userId põhjal")
    public List<GameCompletedInfo> getUserCompletedGames(Integer userId) {
        return gameService.getUserCompletedGames(userId);
    }

    @PatchMapping("/game/completed")
    @Operation(summary = "Uuendab mängu vastavalt kui m2ng on lõpetatud",
            description = "Lisab tabelisse mängu lõpetamise aja, muudab staatuse ja arvutab punktid")
    public void completeGame (@RequestParam Integer gameId) {
        gameService.completeGame(gameId);
    }

}
