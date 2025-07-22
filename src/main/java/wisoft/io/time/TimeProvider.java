package wisoft.io.time;

import java.time.DayOfWeek;

public interface TimeProvider {
    DayOfWeek getDay();
}