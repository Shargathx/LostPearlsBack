package ee.lostpearls.persistence.game;

import ee.lostpearls.controller.game.dto.GameInfo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {


    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "location.teaser", target = "teaserInfo")
    GameInfo toGameInfo(Game game);

    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    @Mapping(source = "", target = "")
    Game toGame(GameInfo gameInfo);
}