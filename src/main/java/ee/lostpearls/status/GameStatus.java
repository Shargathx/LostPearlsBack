package ee.lostpearls.status;

import lombok.Getter;

@Getter
public enum GameStatus {
    GAME_ADDED("GA"),  // user can activate the game?
    GAME_STARTED("GS"), // user is playing the game?
    GAME_COMPLETED("GC"); // game is finished and dispalyed in "my locations"?


    private final String code;

    GameStatus(String code) {
        this.code = code;
    }
}
