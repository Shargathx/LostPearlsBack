package ee.lostpearls.infrastructure.exception;

import lombok.Getter;

@Getter
public class PrimaryKeyNotFoundException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public PrimaryKeyNotFoundException(String fieldName, Integer fieldValue) {
        super("Primary key not found: " + fieldName + "väärtusega: " + fieldValue);
        this.message = "Primary key not found: '" + fieldName + "'väärtusega: " + fieldValue;
        this.errorCode = 777;
    }
}
