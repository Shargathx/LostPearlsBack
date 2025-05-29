package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.controller.game.dto.GameStartDto;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.persistence.game.GameMapper;
import ee.lostpearls.persistence.game.GameRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.locationimage.LocationImageRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import ee.lostpearls.status.GameStatus;
import ee.lostpearls.util.InstantTime;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final LocationImageRepository locationImageRepository;


    public GameInfo findGame(Integer gameId) {
        Game game = getValidGameBy(gameId);
        GameInfo gameInfo = gameMapper.toGameInfo(game);
        return gameInfo;
    }

    @Transactional
    public Game addGame(Integer userId, Integer locationId) {
        Game game = createAndSaveGame(userId, locationId);
        return game;
    }


    private Game getValidGameBy(Integer gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
    }

    private Game createAndSaveGame(Integer userId, Integer locationId) {
        Game game = createGame(userId, locationId);
        gameRepository.save(game);
        return game;
    }

    private Game createGame(Integer userId, Integer locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId", locationId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
        Game game = new Game();
        game.setLocation(location);
        game.setUser(user);
        game.setStatus(GameStatus.GAME_ADDED.getCode());
        game.setPoints(0);
        return game;
    }

    public void startGame(Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
        game.setStartTime(Instant.now());
        //.plus(3, ChronoUnit.HOURS) Sellega tuli frondis timeriga error. j2tan igaks juhuks alles.
        game.setStatus(GameStatus.GAME_STARTED.getCode());
        gameRepository.save(game);
    }

}
