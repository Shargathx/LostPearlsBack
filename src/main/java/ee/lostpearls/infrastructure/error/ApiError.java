package ee.lostpearls.infrastructure.error;

import lombok.Data;

@Data
public class ApiError {
    private String message; // NULL
    private Integer errorCode; // NULL
}

