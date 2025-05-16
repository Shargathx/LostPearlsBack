package ee.lostpearls.infrastructure.exception;

import lombok.Getter;

@Getter
public class ForeignKeyNotFoundException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public ForeignKeyNotFoundException(String fieldName, Integer fieldValue) {
        super("Foreign key not found: " + fieldName + "väärtusega: " + fieldValue);
        this.message = "Foreign key not found: '" + fieldName + "'väärtusega: " + fieldValue;
        this.errorCode = 666;
    }
}
