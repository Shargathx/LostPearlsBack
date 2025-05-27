package ee.lostpearls.persistence.game;

import ee.lostpearls.controller.game.dto.GameInfo;
import ee.lostpearls.controller.game.dto.GameSetupDto;
import ee.lostpearls.status.GameStatus;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {GameStatus.class})

public interface GameMapper {


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
    // @Mapping(pilt/pildid tulevad pildi teenusest, target = "imageData")
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


    @Mapping(constant = "0", target = "points")
    @Mapping(expression = "java(GameStatus.GAME_ADDED.getCode())", target = "status")
    Game toGame(GameSetupDto gameSetupDto);
}