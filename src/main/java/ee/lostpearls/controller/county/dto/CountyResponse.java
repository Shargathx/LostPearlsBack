package ee.lostpearls.controller.county.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CountyResponse {
    private  Integer countyId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer zoomlevel;
}
