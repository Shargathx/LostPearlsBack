package ee.lostpearls.service;

import ee.lostpearls.controller.game.dto.GameCompletedInfo;
import ee.lostpearls.controller.game.dto.GamesInProgressInfo;
import ee.lostpearls.controller.game.dto.GameCardInfo;
import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.infrastructure.exception.DataNotFoundException;
import ee.lostpearls.infrastructure.exception.ForbiddenException;
import ee.lostpearls.infrastructure.exception.ForeignKeyNotFoundException;
import ee.lostpearls.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.lostpearls.persistence.game.Game;
import ee.lostpearls.persistence.game.GameMapper;
import ee.lostpearls.persistence.game.GameRepository;
import ee.lostpearls.persistence.location.Location;
import ee.lostpearls.persistence.location.LocationRepository;
import ee.lostpearls.persistence.locationimage.LocationImage;
import ee.lostpearls.persistence.locationimage.LocationImageRepository;
import ee.lostpearls.persistence.user.User;
import ee.lostpearls.persistence.user.UserRepository;
import ee.lostpearls.status.GameStatus;
import ee.lostpearls.util.ImageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ee.lostpearls.conf.SystemSettings.SYSTEM_ALLOWED_TOTAL_SLOTS;
import static ee.lostpearls.status.LocationStatus.LOCATION_ADDED;

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

    public GamesInProgressInfo getGamesInProgressInfo(Integer userId) {
        List<String> activeStatuses = List.of(GameStatus.GAME_ADDED.getCode(), GameStatus.GAME_STARTED.getCode());
        List<Game> userGames = gameRepository.findGamesByUserIdAndStatusIn(userId, activeStatuses);


        if (userGames.size() >= 4) {
            throw new ForbiddenException("Süsteemi viga (552)', palun võta ühendust meie teenindusega", 552);
        }

        List<GameCardInfo> gameCardInfos = gameMapper.toGameCardInfos(userGames);

        addImages(gameCardInfos);

        int numberOfConsumedSlots = gameCardInfos.size();

        GamesInProgressInfo gamesInProgressInfo = new GamesInProgressInfo();
        gamesInProgressInfo.setTotalSlots(SYSTEM_ALLOWED_TOTAL_SLOTS);
        gamesInProgressInfo.setConsumedSlots(numberOfConsumedSlots);
        gamesInProgressInfo.setAvailableSlots(SYSTEM_ALLOWED_TOTAL_SLOTS - numberOfConsumedSlots);
        gamesInProgressInfo.setIsNextSlotAvailable(isIsNextSlotAvailable(numberOfConsumedSlots));
        gamesInProgressInfo.setGameCards(gameCardInfos);

        return gamesInProgressInfo;
    }

    private void addImages(List<GameCardInfo> gameCardInfos) {
        for (GameCardInfo gameCardInfo : gameCardInfos) {
            Integer locationId = gameCardInfo.getLocationId();
            LocationImage findLocationImageBy = locationImageRepository.findLocationImageBy(locationId);
            if (findLocationImageBy != null) {
                byte[] imageData = findLocationImageBy.getImageData();
                String locationImageData = ImageConverter.bytesToString(imageData);
                gameCardInfo.setLocationImageData(locationImageData);
            }
        }
    }

    private static boolean isIsNextSlotAvailable(int numberOfConsumedSlots) {
        return numberOfConsumedSlots< SYSTEM_ALLOWED_TOTAL_SLOTS;
    }

    public List<GameCompletedInfo> getUserCompletedGames(Integer userId) {
        List<Game> gamesCompleted = gameRepository.findGamesBy(userId, GameStatus.GAME_COMPLETED.getCode());
        List<GameCompletedInfo> gameCompletedInfos = gameMapper.toGameCompletedInfos(gamesCompleted);
        return gameCompletedInfos ;
    }
}
