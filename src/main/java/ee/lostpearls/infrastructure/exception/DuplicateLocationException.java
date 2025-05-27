package ee.lostpearls.infrastructure.exception;

import lombok.Getter;

@Getter
public class DuplicateLocationException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public DuplicateLocationException() {
        this.message = "Selline asukoht juba eksisteerib!";
        this.errorCode = 45;
    }
}
