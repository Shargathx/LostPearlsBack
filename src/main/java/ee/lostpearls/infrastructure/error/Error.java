package ee.lostpearls.infrastructure.error;

import lombok.Getter;

@Getter
public enum Error {
    INCORRECT_CREDENTIALS("Vale kasutajanimi või parool", 111),
    NO_ATM_LOCATIONS_FOUND("Ei leitud ühtegi pangaautomaati", 222),
    LOCATION_UNAVAILABLE("Selline pangaautomaat juba eksisteerib", 333);



    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

}
