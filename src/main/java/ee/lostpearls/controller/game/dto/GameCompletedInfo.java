package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameCompletedInfo {
    private String locationName;
    private String status;
    private LocalDateTime completeDate;
    private Integer points;

}
