package ee.lostpearls.infrastructure.error;

import lombok.Getter;

@Getter
public enum Error {
    INCORRECT_CREDENTIALS("Vale kasutajanimi või parool", 111),
    NO_LOCATIONS_FOUND("Ei leitud ühtegi asukohta", 222),
    LOCATION_UNAVAILABLE("Selline asukoht juba eksisteerib", 333),
    FOREIGN_KEY_NOT_FOUND( "Foreign key not found: '{fieldName}' with value: '{fieldValue}'", 666),
    NAME_OR_EMAIL_UNAVAILABLE("Sellise nime või emailiga kasutaja juba eksisteerib", 101);



    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
