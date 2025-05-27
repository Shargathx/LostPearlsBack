package ee.lostpearls.controller.location.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RandomLocationResponse {
    private String locationName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private LocalDate dateAdded;
}
