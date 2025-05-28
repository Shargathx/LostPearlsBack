package ee.lostpearls.infrastructure.exception;

import ee.lostpearls.infrastructure.error.Error;
import lombok.Getter;

import static ee.lostpearls.infrastructure.error.Error.FOREIGN_KEY_NOT_FOUND;

@Getter
public class ForeignKeyNotFoundException extends RuntimeException {
    private final String message;
    private final Integer errorCode;

    public ForeignKeyNotFoundException(String fieldName, Integer fieldValue) {
        String resultMessage =  FOREIGN_KEY_NOT_FOUND.getMessage().replace("{fieldName}", fieldName);
        resultMessage = resultMessage.replace("{fieldValue}", String.valueOf(fieldValue));
        this.message = resultMessage;
        this.errorCode = FOREIGN_KEY_NOT_FOUND.getErrorCode();
    }
}
