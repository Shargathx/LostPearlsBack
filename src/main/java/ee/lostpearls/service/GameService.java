package ee.lostpearls.service;

import ee.lostpearls.controller.GameDto;
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


    public GameDto findGame(Integer gameId) {
        Game game = getGame(gameId);
        GameDto gameDto = gameMapper.toGameDto(game);
        return  gameDto;



    }

    private Game getGame(Integer gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
        return game;
    }
}
