package ee.lostpearls.infrastructure.exception;

import lombok.Getter;

@Getter
public class DuplicateLocationException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public DuplicateLocationException(String message, Integer errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
