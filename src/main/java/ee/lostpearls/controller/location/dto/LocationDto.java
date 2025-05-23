package ee.lostpearls.controller.location.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Integer countyId; // required field
    private String locationName; // required field
    private String longitude; // required field
    private String latitude; // required field
    private String teaser; // required field
    private String extendedInfo; // required field
    private String question; // required field
    private String answer; // required field
}
