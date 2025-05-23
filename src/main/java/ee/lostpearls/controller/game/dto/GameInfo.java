package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameInfo {
    private Integer locationId;
    private String locationName;
    private String teaserInfo;
    private String extendedInfo;
    private String question;
    private String answer;
    private String gameStatus;
    private Long gameStartMilliseconds;
    private Long gameEndMilliseconds;
    private LocalDateTime completeDate;
    private Integer points;

}
