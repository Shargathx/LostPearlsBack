package ee.lostpearls.controller.location.dto;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link LocationPostResponse}
 */
@Value
public class LocationPostResponseDto implements Serializable {
    int id;
    Integer countyId;
    String locationName;
    BigDecimal longitude;
    BigDecimal latitude;
    Integer zoomlevel;
    String teaser;
    String extendedInfo;
    String question;
    String answer;
}