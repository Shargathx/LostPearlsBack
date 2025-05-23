package ee.lostpearls.status;

import lombok.Getter;

@Getter
public enum LocationStatus {
    LOCATION_ADDED("A"),
  //  GAME_STARTED("GS"),
    LOCATION_DELETED("D");


    private final String code;

    LocationStatus(String code) {
        this.code = code;
    }
}
