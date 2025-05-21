package ee.lostpearls.controller;

import lombok.Data;

@Data
public class LocationDto {
    private String locationName;
    private String countyName;
    private Double longitude;
    private Double latitude;
    private String teaser;
    private String fullDescription;
    private String question;
    private String answer;
    private String imageData;



}
