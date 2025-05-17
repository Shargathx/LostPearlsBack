package ee.lostpearls;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("A"),  // user can activate the game?
    ONGOING("B"), // user is playing the game?
    COMPLETED("C"); // game is finished and dispalyed in "my locations"?

    private final String code;

    Status(String code) {
        this.code = code;
    }
}
