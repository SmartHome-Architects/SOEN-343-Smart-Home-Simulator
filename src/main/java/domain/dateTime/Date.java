package domain.dateTime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    private int day;
    private int month;
    private int year;
    private String dayOfWeek;

    public Date() {
        LocalDate currentDate = LocalDate.now();
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();
        this.dayOfWeek = currentDate.getDayOfWeek().toString();
    }

    public Date(int day, int month, int year, String dayOfWeek) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayOfWeek = dayOfWeek;
    }

    public Date(String dateString) {
        String[] parts = dateString.split(", ");
        this.dayOfWeek = parts[0];
        String datePart = parts[1];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(datePart, formatter);

        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String toString() {
        return String.format("%s, %02d/%02d/%04d", dayOfWeek, day, month, year);
    }
}
