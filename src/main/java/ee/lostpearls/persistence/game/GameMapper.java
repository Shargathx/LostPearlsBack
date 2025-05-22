package ee.lostpearls.persistence.game;

import ee.lostpearls.controller.GameDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {


    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.name", target = "locationName")
    @Mapping(source = "location.teaser", target = "teaserInfo")
    @Mapping(ignore = true, target = "imageData")
    GameDto toGameDto(Game game);


}