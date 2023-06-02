package com.ggggght.learningjava8.util;

import java.time.Duration;
import java.util.Comparator;
import java.util.Objects;

record RaceTime(String runnerName, Duration time) {}

class RaceTimeComparator implements Comparator<RaceTime> {
    @Override
    public int compare(RaceTime o1, RaceTime o2) {
        return o1.time().compareTo(o2.time());
    }
}

public class ObjectsUsage {
    public static void main(String[] args) {
        RaceTime nullValue = null;
        RaceTime billy  = new RaceTime("Billy", Duration.ofSeconds(200));
        RaceTime copyOfbilly  = new RaceTime("Billy", Duration.ofSeconds(200));
        RaceTime nicolai = new RaceTime("Nicolai", Duration.ofSeconds(100));

        nullValue.equals(billy); // NPE
        Objects.equals(nullValue, billy); // false
        Objects.equals(nicolai, billy); // false
        Objects.equals(copyOfbilly, billy); // true

        RaceTime[] raceTimes1 = new RaceTime[] {billy, nicolai};
        RaceTime[] raceTimes2 = new RaceTime[] {billy, nicolai};

        Objects.equals(raceTimes1, raceTimes2); // false
        Objects.deepEquals(raceTimes1, raceTimes2); // true

        Objects.compare(billy, nicolai, new RaceTimeComparator()); // 1
        Objects.compare(null, nicolai, new RaceTimeComparator()); // NPE

        Objects.toString(billy); // RaceTime[runnerName=Billy, time=PT3M20S]
        Objects.toString(nullValue); // null
        Objects.toString(nullValue,"nullValue"); // nullValue
        Objects.toIdentityString(billy); // com.ggggght.learningjava8.util.RaceTime@161cd475

        Objects.hashCode(nullValue);
        Objects.hashCode(billy);
        Objects.hash(billy, nicolai);

        Objects.requireNonNull(nullValue);
        Objects.requireNonNull(nullValue, "nullValue");
        Objects.requireNonNull(nullValue, () -> "nullValue");
        Objects.requireNonNullElseGet(nullValue, () -> new RaceTime("default", Duration.ofSeconds(0)));
    }
}
