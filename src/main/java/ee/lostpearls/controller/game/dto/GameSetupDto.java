package ee.lostpearls.controller.game.dto;

import lombok.Data;

@Data
public class GameSetupDto {
    private Integer locationId;
    private Integer userId;
    private String gameStatus;
    private Long gameStartMilliseconds;
    private Integer points;
}
