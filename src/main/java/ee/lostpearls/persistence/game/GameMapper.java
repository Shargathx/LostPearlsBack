package ee.lostpearls.persistence.game;

import ee.lostpearls.controller.game.dto.GameCardInfo;
import ee.lostpearls.controller.game.dto.GameCompletedInfo;
import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.status.GameStatus;
import org.mapstruct.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {GameStatus.class, Instant.class, ZoneId.class})

public interface GameMapper {


    @Mapping(source = "location.county.name", target = "countyName")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "location.teaser", target = "teaserInfo")
    @Mapping(source = "location.extendedInfo", target = "extendedInfo")
    @Mapping(source = "location.question", target = "question")
    @Mapping(source = "location.answer", target = "answer")
    @Mapping(source = "points", target = "points")
    @Mapping(source = "completeDate", target = "completeDate")
    @Mapping(source = "status", target = "gameStatus")
    @Mapping(source = "startTime", target = "gameStartMilliseconds")
    @Mapping(source = "endTime", target = "gameEndMilliseconds")
    @Mapping(source = "location.latfield", target = "lat")
    @Mapping(source = "location.longfield", target = "lng")
    @Mapping(constant = "", target = "imageData")
    @Mapping(constant = "14", target = "zoomLevel")
        // @Mapping( hint tuleb eraldi teenusega hing v game_hint tabelist, target = "hint")
    GameInfo toGameInfo(Game game);

    /*/ Converts Instant to Long(timestamp in millisec)
    Instant value → Takes an Instant type (used for time representations).
    Long return type → Converts the Instant into a numeric timestamp (milliseconds).
    The ternary operator (? :) ensures the method does not try to convert a null value, preventing NullPointerException.
    If value is NOT null, proceed with conversion.
    If value is null, return null     */

    default Long map(Instant value) {
        return value != null ? value.toEpochMilli() : null;
    }


    @Mapping(source = "status", target = "status")
    @Mapping(source = "location.longfield", target = "countyLng")
    @Mapping(source = "location.latfield", target = "countyLat")
    @Mapping(source = "location.county.name", target = "countyName")
    @Mapping(source = "location.name", target = "locationName")
    GameCardInfo toGameCardInfo(Game game);

    List<GameCardInfo> toGameCardInfos(List<Game> games);


    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "completeDate", target = "completeDate")
    @Mapping(source = "points", target = "points")
    GameCompletedInfo toGameCompletedInfo(Game game);

    List<GameCompletedInfo> toGameCompletedInfos(List<Game> gamesCompleted);
}