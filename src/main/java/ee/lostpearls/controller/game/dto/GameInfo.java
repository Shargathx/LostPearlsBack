package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GameInfo {
    private Integer locationId;
    private String locationName;
    private String countyName;
    private String teaserInfo;
    private String extendedInfo;
    private String question;
    private String answer;
    private String gameStatus;
    private Long gameStartMilliseconds;
    private Long gameEndMilliseconds;
    private LocalDateTime completeDate;
    private Integer points;
    private String imageData;
    private BigDecimal lat;
    private BigDecimal lng;
    private Integer zoomLevel;

    //private List<HintInfo> hints; // comment this in later
}
