package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.util.List;

@Data
public class GamesInProgressInfo {
    private Integer totalSlots;
    private Integer consumedSlots;
    private Integer availableSlots;
    private Boolean isNextSlotAvailable;
    private List<GameCardInfo> gameCards;
}
