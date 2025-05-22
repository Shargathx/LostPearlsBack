package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.persistence.game.GameMapper;
import ee.lostpearls.persistence.game.GameRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final LocationRepository locationRepository;


    public GameInfo findGame(Integer gameId) {
        Game game = getValidGameBy(gameId);
        return gameMapper.toGameInfo(game);

    }
    private Game getValidGameBy(Integer gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
    }

    public Game createAndSaveGame(GameInfo gameInfo) {
        Game game = createGame(gameInfo);
        gameRepository.save(game);
        return game;
    }
    private Game createGame(GameInfo gameInfo) {
        Integer locationId = gameInfo.getLocationId();
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new PrimaryKeyNotFoundException("locationId", locationId));
        Game game = gameMapper.toGame(gameInfo);
        game.setLocation(location);
        return game;
    }
}
