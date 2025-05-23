package ee.lostpearls.status;

import lombok.Getter;

@Getter
public enum GameStatus {
    GAME_ADDED("GA"),  // user can activate the game?
    GAME_STARTED("GS"),  // user can activate the game?
    GAME_COMPLETED("GC");  // user can activate the game?

    private final String code;

    GameStatus(String code) {
        this.code = code;
    }
}
