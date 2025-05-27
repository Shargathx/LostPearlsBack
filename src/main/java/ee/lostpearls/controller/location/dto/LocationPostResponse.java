package ee.lostpearls.controller.location.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class LocationPostResponse {
    private int id;
    private Integer countyId; // required field
    private String locationName; // required field
    private BigDecimal longitude; // required field
    private BigDecimal latitude; // required field
    private Integer zoomlevel;
    private String teaser; // required field
    private String extendedInfo; // required field
    private String question; // required field
    private String answer; // required field
}
