package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.util.List;

@Data
public class GamesInProgress {
    private Integer totalSlots = 3;
    private Integer consumedSlots;
    private Boolean isNextSlotAvailable;
    private List<GameCardInfo> gameCards;
}
