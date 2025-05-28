package ee.lostpearls.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantTime {

    public static Instant getTallinTimeZoneInstant() {
        ZoneId estoniaZone = ZoneId.of("Europe/Tallinn");
        return ZonedDateTime.now(estoniaZone).toInstant();
    }
}
