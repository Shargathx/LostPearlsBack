package ee.lostpearls.infrastructure.exception;

import lombok.Getter;

@Getter
public class PrimaryKeyNotFoundException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public PrimaryKeyNotFoundException(String fieldName, Integer fieldValue) {
        super("Primary key: " + fieldName + "väärtusega: " + fieldValue + " not found!");
        this.message = "Primary key: '" + fieldName + "' with value: '" + fieldValue + "' not found!";
        this.errorCode = 450;
    }
}
