package ee.lostpearls;

import lombok.Getter;

@Getter
public enum GameStatus {
    ACTIVE("A"),  // user can activate the game?
    ONGOING("O"), // user is playing the game?
    COMPLETED("C"); // game is finished and dispalyed in "my locations"?


    private final String code;

    GameStatus(String code) {
        this.code = code;
    }
}
