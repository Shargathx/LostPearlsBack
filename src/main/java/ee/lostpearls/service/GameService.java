package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.persistence.game.GameMapper;
import ee.lostpearls.persistence.game.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;


    public GameInfo findGame(Integer gameId) {
        Game game = getValidGameBy(gameId);
        GameInfo gameInfo = gameMapper.toGameInfo(game);
        return gameInfo;



    }

    private Game getValidGameBy(Integer gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
    }

}
