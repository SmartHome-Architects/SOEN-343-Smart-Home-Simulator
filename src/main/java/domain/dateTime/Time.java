package src.main.java.domain.dateTime;

import java.time.LocalTime;
public class Time {

        private int hour;
        private int minute;
        private int second;

        public Time() {
            LocalTime currentTime = LocalTime.now();
            this.hour = currentTime.getHour();
            this.minute = currentTime.getMinute();
            this.second = currentTime.getSecond();
        }

        public Time(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public String toString() {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
    }

}
