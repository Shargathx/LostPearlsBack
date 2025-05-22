package ee.lostpearls.controller;

import lombok.Data;

@Data
public class GameDto {
    private Integer locationId;
    private String locationName;
    private String teaserInfo;
    private String imageData;
}
