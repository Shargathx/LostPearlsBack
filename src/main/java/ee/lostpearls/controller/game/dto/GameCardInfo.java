package ee.lostpearls.controller.game.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GameCardInfo {
    private String locationName;
    private String countyName;
    private BigDecimal countyLat;
    private BigDecimal countyLng;
    private String status;

}
