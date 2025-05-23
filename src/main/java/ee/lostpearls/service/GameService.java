package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.controller.game.dto.GameSetupDto;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.persistence.game.GameMapper;
import ee.lostpearls.persistence.game.GameRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;


    public GameInfo findGame(Integer gameId) {
        Game game = getValidGameBy(gameId);
        return gameMapper.toGameInfo(game);
    }

    @Transactional
    public Game addGame(GameSetupDto gameSetupDto) {
        Game game = createAndSaveGame(gameSetupDto);
        return game;
    }


    private Game getValidGameBy(Integer gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
    }

    private Game createAndSaveGame(GameSetupDto gameSetupDto) {
        Game game = createGame(gameSetupDto);
        gameRepository.save(game);
        return game;
    }
    private Game createGame(GameSetupDto gameSetupDto) {
        Integer locationId = gameSetupDto.getLocationId();
        Integer userId = gameSetupDto.getUserId();
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("locationId", locationId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
        Game game = gameMapper.toGame(gameSetupDto);
        game.setLocation(location);
        game.setUser(user);
        return game;
    }
}
