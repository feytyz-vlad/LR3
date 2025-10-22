package ua.com.kisit.lab3;

import java.util.Calendar;

public class ClockExt2 extends ClockExt {

    private int milliseconds;

    public ClockExt2() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int milli = now.get(Calendar.MILLISECOND);

        super(hour, minute, second);
        this.milliseconds = milli;
    }

    public void nextMilliSecond() {
        milliseconds += 10;

        if (milliseconds >= 1000) {
            nextSeconds();
            milliseconds = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d.%03d",
                getHour(), getMinute(), getSeconds(), milliseconds);
    }
}