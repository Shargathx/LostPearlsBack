package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameCompletedInfo;
import ee.lostpearls.controller.game.dto.GamesInProgress;
import ee.lostpearls.controller.game.dto.GameCardInfo;
import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.infrastructure.exception.DataNotFoundException;
import ee.lostpearls.infrastructure.exception.ForeignKeyNotFoundException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ee.lostpearls.status.LocationStatus.LOCATION_ADDED;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final LocationImageRepository locationImageRepository;

    private static final int MAX_POINTS = 100;
    private static final int MIN_POINTS = 1;
    private static final int MAX_TIME_MINUTES = 60;
    private static final int POINT_DEDUCTION_INTERVAL_SECONDS = 36;
    private static final int HINT_PENALTY = 10;



    public GameInfo findGame(Integer gameId) {
        Game game = getValidGameBy(gameId);
        GameInfo gameInfo = gameMapper.toGameInfo(game);
        return gameInfo;
    }

    public void addGame(Integer countyId, Integer userId) {
        Integer randomLocationId = findRandomLocationId(countyId, userId);
        Location location = locationRepository.getReferenceById(randomLocationId);
        Game game = createGame(userId, location);
        gameRepository.save(game);
    }


    private Integer findRandomLocationId(Integer countyId, Integer userId) {
        List<Integer> candidateLocationIds = findCandidateLocationIds(countyId, userId);
        if (candidateLocationIds.isEmpty()) {
            throw new DataNotFoundException("Süsteemis ei ole hetkel saadaval enam ühtegi uut asukohta. Külasta meid hiljem uuesti", 999);
        }
        return findNextRandomLocationId(candidateLocationIds);
    }

    private static Integer findNextRandomLocationId(List<Integer> candidateLocationIds) {
        Random random = new Random();
        int randomIndex = random.nextInt(candidateLocationIds.size());
        Integer nextRandomLocationId = candidateLocationIds.get(randomIndex);
        return nextRandomLocationId;
    }

    private List<Integer> findCandidateLocationIds(Integer countyId, Integer userId) {
        List<Integer> availableLocationIds = locationRepository.findLocationIdsByCountyIdAndStatusAndExcludeUserId(countyId, LOCATION_ADDED.getCode(), userId);
        List<Integer> unavailableLocationIds = gameRepository.findLocationIdsBy(userId);
        List<Integer> candidateLocationIds = new ArrayList<>(availableLocationIds);
        candidateLocationIds.removeAll(unavailableLocationIds);
        return candidateLocationIds;
    }


    private Game createGame(Integer userId, Location location) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ForeignKeyNotFoundException("userId", userId));
        Game game = new Game();
        game.setLocation(location);
        game.setUser(user);
        game.setStatus(GameStatus.GAME_ADDED.getCode());
        game.setPoints(0);
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

    public void completeGame(Integer gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("gameId", gameId));
        game.setEndTime(Instant.now());
        game.setStatus(GameStatus.GAME_COMPLETED.getCode());
        Instant startTime = game.getStartTime();
        Instant endTime = game.getEndTime();
        Integer hintsUsed = game.getHintsUsed();
        int finalPoints = calculatePoints(startTime, endTime, hintsUsed);
        game.setPoints(finalPoints);
        gameRepository.save(game);

    }

    private static int calculatePoints(Instant startTime, Instant endTime, Integer hintsUsed) {
        long elapsedMilliseconds = endTime.toEpochMilli() - startTime.toEpochMilli();
        long elapsedSeconds = elapsedMilliseconds * 1000;
        long elapsedMinutes = elapsedSeconds * 60;
        // If the answer is submitted after 60 minutes, no points are awarded
        if (elapsedMinutes >= MAX_TIME_MINUTES) {
            return 0;
        }

        // Calculate points based on elapsed time
        int points = MAX_POINTS - (int) (elapsedSeconds / POINT_DEDUCTION_INTERVAL_SECONDS);

        // Ensure points don't go below the minimum threshold
        points = Math.max(points, MIN_POINTS);

        // Deduct points for hints used
        points -= hintsUsed * HINT_PENALTY;

        // Ensure points don't go negative
        return Math.max(points, 0);
    }


    public List<GameCardInfo> getUserGamesInProgress(Integer userId) {
        List<String> activeStatuses = List.of(GameStatus.GAME_ADDED.getCode(), GameStatus.GAME_STARTED.getCode());
        List<Game> userGames = gameRepository.findByUserIdAndStatusIn(userId, activeStatuses);

        if (userGames.isEmpty()) {
            throw new DataNotFoundException("List on tühi, proovi hiljem uuesti", 551);
        }

        List<GameCardInfo> gameCardInfos = gameMapper.toGameCardInfos(userGames);

        GamesInProgress gamesInProgress = new GamesInProgress();
        gamesInProgress.setTotalSlots(3);
        gamesInProgress.setConsumedSlots(gameCardInfos.size());
        gamesInProgress.setIsNextSlotAvailable(gameCardInfos.size() < gamesInProgress.getTotalSlots());
        gamesInProgress.setGameCards(gameCardInfos);

        return gamesInProgress.getGameCards();
    }

    public List<GameCompletedInfo> getUserCompletedGames(Integer userId) {
        List<Game> gamesCompleted = gameRepository.findGamesBy(userId, GameStatus.GAME_COMPLETED.getCode());
        List<GameCompletedInfo> gameCompletedInfos = gameMapper.toGameCompletedInfos(gamesCompleted);
        return gameCompletedInfos ;
    }

}
