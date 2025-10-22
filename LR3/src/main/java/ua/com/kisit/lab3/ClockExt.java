package ua.com.kisit.lab3;

public class ClockExt extends Clock {

    private int seconds;

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public ClockExt() {}

    public ClockExt(int seconds) {
        this.seconds = seconds;
    }

    public ClockExt(int hour, int minute, int seconds) {
        super(hour, minute);
        this.seconds = seconds;
    }

    public void nextSeconds() {
        seconds++;
        if (seconds > 59) {
            nextMinute();
            seconds = 0;
        }
    }

    @Override
    public String toString() {
        return "ClockExt{" +
                "hour=" + getHour() +
                ", minute=" + getMinute() +
                ", seconds=" + seconds +
                '}';
    }

    public static void main(String[] args) {

        ClockExt clockExt = new ClockExt(11,56,34);
        System.out.println(clockExt);

    }
}
