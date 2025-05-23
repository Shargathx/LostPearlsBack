package ee.lostpearls.controller.county.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CountyInfo {
    private Integer countyId;
    private String countyName;

}
