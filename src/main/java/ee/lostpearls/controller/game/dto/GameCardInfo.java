package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GameCardInfo {
    private Integer gameId;
    private String countyName;
    private String locationName;
    private BigDecimal locationLat;
    private BigDecimal locationLng;
    private String status;

}
