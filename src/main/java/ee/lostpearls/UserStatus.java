package ee.lostpearls;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("C"),
    DEACTIVATED("D");


    private final String code;

    UserStatus(String code) {
        this.code = code;
    }
}
