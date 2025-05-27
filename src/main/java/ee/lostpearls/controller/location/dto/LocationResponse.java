package ee.lostpearls.controller.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class LocationResponse {
    private int locationId;
    private String locationName;
    private String countyName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Integer zoomlevel;
    private String teaser;
    private String extendedInfo;
    private String question;
    private String answer;
    private String status;
    private LocalDate dateAdded;
}